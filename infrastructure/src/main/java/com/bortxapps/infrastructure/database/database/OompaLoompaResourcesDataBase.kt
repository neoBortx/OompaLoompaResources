package com.bortxapps.infrastructure.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bortxapps.infrastructure.database.dao.OompaLoompaInfoDao
import com.bortxapps.infrastructure.database.dao.RemoteKeyDao
import com.bortxapps.infrastructure.database.entities.OompaLoompaInfoEntity
import com.bortxapps.infrastructure.database.entities.RemoteKeyEntity

/**
 * Cache data base to reduce network request and allow user to work in offline mode
 */
@Database(entities = [OompaLoompaInfoEntity::class, RemoteKeyEntity::class], version = 2)
abstract class OompaLoompaResourcesDataBase : RoomDatabase() {

    abstract fun oompaLoompaDao(): OompaLoompaInfoDao
    abstract fun remoteKeyDao(): RemoteKeyDao

    companion object {

        const val DATABASE_NAME = "oompaLoompasResources.db"
    }
}