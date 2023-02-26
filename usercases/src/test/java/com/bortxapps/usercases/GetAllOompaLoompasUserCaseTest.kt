package com.bortxapps.usercases

import com.bortxapps.respository.OompaLoompasRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.Assert.*

class GetAllOompaLoompasUserCaseTest {

    @MockK
    lateinit var oompaLoompasRepository: OompaLoompasRepository

    lateinit var getAllOompaLoompasUserCase: GetAllOompaLoompasUserCase

    @Before
    fun start() {
        MockKAnnotations.init(this)
        getAllOompaLoompasUserCase = GetAllOompaLoompasUserCase(oompaLoompasRepository)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test user case calls repository`() = runTest {

        every { oompaLoompasRepository.getAll() } returns flow {
            emit(mockk())
        }

        getAllOompaLoompasUserCase.invoke()

        verify { oompaLoompasRepository.getAll() }
    }
}