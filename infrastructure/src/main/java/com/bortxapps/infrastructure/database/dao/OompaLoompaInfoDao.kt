package com.bortxapps.infrastructure.database.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.bortxapps.infrastructure.database.entities.OompaLoompaInfoEntity

@Dao
interface OompaLoompaInfoDao {

    @Transaction
    suspend fun addAll(entities: List<OompaLoompaInfoEntity>) {
        clearTable()
        insertAll(entities)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<OompaLoompaInfoEntity>)

    @Query("DELETE FROM oompaloompas")
    suspend fun clearTable()

    @Query("SELECT * FROM oompaloompas")
    fun getAll(): PagingSource<Int, OompaLoompaInfoEntity>

    @Query("SELECT * FROM oompaloompas WHERE id LIKE :id")
    suspend fun getDetail(id: Int): OompaLoompaInfoEntity?
}