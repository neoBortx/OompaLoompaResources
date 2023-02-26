package com.bortxapps.usercases

import com.bortxapps.respository.OompaLoompasRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.Assert.*

class GetOompaLoompaDetailUserCaseTest {

    @MockK
    lateinit var oompaLoompasRepository: OompaLoompasRepository

    lateinit var getAllOompaLoompasUserCase: GetOompaLoompaDetailUserCase

    @Before
    fun start() {
        MockKAnnotations.init(this)
        getAllOompaLoompasUserCase = GetOompaLoompaDetailUserCase(oompaLoompasRepository)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test user case calls repository`() = runTest {

        coEvery { oompaLoompasRepository.getDetail(5) } returns flow {
            emit(mockk())
        }

        getAllOompaLoompasUserCase.invoke(5)

        coVerify { oompaLoompasRepository.getDetail(5) }
    }
}