package com.bortxapps.oompaloomparesources.viewmodels

import com.bortxapps.data.OompaLoompaInfo
import com.bortxapps.data.Tastes
import com.bortxapps.usercases.GetOompaLoompaDetailUserCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.*

class OompaLoompaDetailViewModelTest {

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @MockK
    lateinit var getOompaLoompaDetailUserCase: GetOompaLoompaDetailUserCase
    lateinit var viewModel: OompaLoompaDetailViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun start() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test initial estate expect IDLE`() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        viewModel = spyk(OompaLoompaDetailViewModel(getOompaLoompaDetailUserCase, dispatcher))

        assertTrue(viewModel.state is DetailState.Idle)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `no data expect error estate`() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        viewModel = spyk(OompaLoompaDetailViewModel(getOompaLoompaDetailUserCase, dispatcher))

        coEvery { getOompaLoompaDetailUserCase(5) } returns flow {
            emit(null)
        }


        viewModel.loadData(5)
        advanceUntilIdle()
        assertTrue(viewModel.state is DetailState.Error)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `detail received expect state with detail`() = runTest() {
        val dispatcher = StandardTestDispatcher(testScheduler)
        viewModel = spyk(OompaLoompaDetailViewModel(getOompaLoompaDetailUserCase, dispatcher))

        val expectedOompa = generateOompa()
        coEvery { getOompaLoompaDetailUserCase(5) } returns flow {
            emit(expectedOompa)
        }

        viewModel.loadData(5)
        advanceUntilIdle()

        assertTrue(viewModel.state is DetailState.DetailReceived)
        assertEquals(expectedOompa, (viewModel.state as DetailState.DetailReceived).item)
    }

    private fun generateOompa(): OompaLoompaInfo =
        OompaLoompaInfo(
            id = 5,
            firstName = "Margarita",
            lastName = "The cow",
            image = "https://fakeUrl.com",
            email = "a@a",
            age = 56,
            profession = "cow",
            height = 56,
            country = "Spain",
            gender = "F",
            tastes = Tastes(
                color = "blue",
                food = "grass",
                randomString = "jashdfkjashdfkjashfkasrg",
                song = "La macarena"
            )
        )
}