package com.bortxapps.respository

import androidx.paging.*
import com.bortxapps.data.OompaLoompaInfo
import com.bortxapps.infrastructure.database.mappers.toDomain
import com.bortxapps.infrastructure.datasource.OompaLompasLocalDataSource
import com.bortxapps.infrastructure.datasource.OompaLoompasRemoteService
import com.bortxapps.respository.mediator.OompaLoompaRemoteMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OompaLoompasRepository @Inject constructor(
    private val dataBase: OompaLompasLocalDataSource,
    private val remoteService: OompaLoompasRemoteService,
    private val mediator: OompaLoompaRemoteMediator
) : IOompaLoompasRepository {

    /**
     * Get the information of a remote service. If the service is not available
     * we use a local storage copy with the last retrieved page
     *
     * Remarks: Because this is the repository (the realm of the data) we don't apply any business
     * logic here
     */
    @OptIn(ExperimentalPagingApi::class)
    override fun getAll(): Flow<PagingData<OompaLoompaInfo>> {
        return Pager(
            PagingConfig(15),
            remoteMediator = mediator
        ) {
            dataBase.getAllOompaLoompas()
        }.flow
            .map { page -> page.map { it.toDomain() } }
    }

    override suspend fun getDetail(id: Int): Flow<OompaLoompaInfo?> {
        val remoteData = remoteService.getDetail(id)

        return flow {
            emit(
                remoteData ?: dataBase.getDetail(id)
            )
        }
    }
}