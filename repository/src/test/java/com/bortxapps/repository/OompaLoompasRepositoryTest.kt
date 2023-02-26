package com.bortxapps.repository

import com.bortxapps.data.OompaLoompaInfo
import com.bortxapps.infrastructure.datasource.OompaLompasLocalDataSource
import com.bortxapps.infrastructure.datasource.OompaLoompasRemoteService
import com.bortxapps.respository.OompaLoompasRepository
import com.bortxapps.respository.mediator.OompaLoompaRemoteMediator
import io.mockk.*
import io.mockk.impl.annotations.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.Assert.*

class OompaLoompasRepositoryTest {

    private lateinit var oompaLoompasRepository: OompaLoompasRepository

    @MockK
    private lateinit var dataBase: OompaLompasLocalDataSource

    @MockK
    private lateinit var remoteService: OompaLoompasRemoteService

    @MockK
    private lateinit var mediator: OompaLoompaRemoteMediator

    @Before
    fun start() {
        MockKAnnotations.init(this)
        oompaLoompasRepository = OompaLoompasRepository(dataBase, remoteService, mediator)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test remote service returns null expect data retrieved from database`() = runTest {

        val expectedData = mockk<OompaLoompaInfo>()

        coEvery { remoteService.getDetail(5) } returns null
        coEvery { dataBase.getDetail(5) } returns expectedData

        var result: OompaLoompaInfo? = null

        oompaLoompasRepository.getDetail(5).collect {
            result = it
        }


        assertEquals(expectedData, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test remote service returns value expect data retrieved from network`() = runTest {

        val expectedData = mockk<OompaLoompaInfo>()
        val otherData = mockk<OompaLoompaInfo>()

        coEvery { remoteService.getDetail(5) } returns expectedData
        coEvery { dataBase.getDetail(5) } returns otherData

        var result: OompaLoompaInfo? = null

        oompaLoompasRepository.getDetail(5).collect {
            result = it
        }


        assertEquals(expectedData, result)
    }
}