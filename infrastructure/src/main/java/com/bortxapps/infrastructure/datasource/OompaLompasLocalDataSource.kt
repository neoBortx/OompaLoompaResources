package com.bortxapps.infrastructure.datasource

import androidx.room.withTransaction
import com.bortxapps.data.OompaLoompaInfo
import com.bortxapps.data.OompaLoompaInfoListWrapper
import com.bortxapps.infrastructure.database.database.OompaLoompaResourcesDataBase
import com.bortxapps.infrastructure.database.entities.RemoteKeyEntity
import com.bortxapps.infrastructure.database.mappers.toDomain
import com.bortxapps.infrastructure.database.mappers.toEntity
import javax.inject.Inject

class OompaLompasLocalDataSource @Inject constructor(private val dataBase: OompaLoompaResourcesDataBase) {

    private val oompaLoompaDao = dataBase.oompaLoompaDao()
    private val keyEntityDao = dataBase.remoteKeyDao()

    suspend fun getDetail(id: Int): OompaLoompaInfo? =
        oompaLoompaDao.getDetail(id)?.toDomain()

    fun getAllOompaLoompas() = oompaLoompaDao.getAll()

    suspend fun insertAll(data: OompaLoompaInfoListWrapper) {

        dataBase.withTransaction {
            keyEntityDao.insertKey(
                RemoteKeyEntity(
                    currentPage = data.currentPage,
                    totalPages = data.totalPages
                )
            )
            oompaLoompaDao.insertAll(data.oompaLoompas.toEntity())
        }
    }

    suspend fun clearData() {
        oompaLoompaDao.clearTable()
        keyEntityDao.clearTable()
    }

    suspend fun getKey() = keyEntityDao.getKey()
}