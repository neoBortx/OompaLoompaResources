package com.bortxapps.infrastructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bortxapps.infrastructure.database.entities.RemoteKeyEntity

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(key: RemoteKeyEntity)

    @Query("delete from remote_key")
    suspend fun clearTable()

    @Query("SELECT * FROM remote_key LIMIT 1")
    suspend fun getKey(): RemoteKeyEntity?
}