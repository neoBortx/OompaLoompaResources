package com.bortxapps.respository.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.bortxapps.infrastructure.database.entities.OompaLoompaInfoEntity
import com.bortxapps.infrastructure.datasource.OompaLompasLocalDataSource
import com.bortxapps.infrastructure.datasource.OompaLoompasRemoteService
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class OompaLoompaRemoteMediator @Inject constructor(
    private val dataBase: OompaLompasLocalDataSource,
    private val remoteService: OompaLoompasRemoteService,
) : RemoteMediator<Int, OompaLoompaInfoEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, OompaLoompaInfoEntity>
    ): MediatorResult {
        return try {
            val pageToLoad = when (loadType) {
                LoadType.REFRESH -> {
                    dataBase.clearData()
                    1
                }
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.APPEND -> {
                    val remoteKey = dataBase.getKey()!!

                    if (remoteKey.currentPage == remoteKey.totalPages) {
                        return MediatorResult.Success(true)
                    }
                    remoteKey.currentPage.plus(1)
                }
            }

            remoteService.getAll(pageToLoad)?.let {
                dataBase.insertAll(it)
            }

            MediatorResult.Success(false)
        } catch (e: java.lang.Exception) {
            MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        val remoteKey = dataBase.getKey()
        return if (remoteKey == null) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }
}