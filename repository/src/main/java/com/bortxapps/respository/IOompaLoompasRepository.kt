package com.bortxapps.respository

import androidx.paging.PagingData
import com.bortxapps.data.OompaLoompaInfo
import kotlinx.coroutines.flow.Flow

interface IOompaLoompasRepository {

    fun getAll(): Flow<PagingData<OompaLoompaInfo>>

    suspend fun getDetail(id: Int): Flow<OompaLoompaInfo?>
}