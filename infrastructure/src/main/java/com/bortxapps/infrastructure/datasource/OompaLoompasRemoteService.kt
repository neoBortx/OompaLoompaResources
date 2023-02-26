package com.bortxapps.infrastructure.datasource

import com.bortxapps.infrastructure.remote.api.OompaLoompaInfoApi
import com.bortxapps.infrastructure.remote.mappers.toDomain
import javax.inject.Inject

class OompaLoompasRemoteService @Inject constructor(private val api: OompaLoompaInfoApi) {

    suspend fun getAll(page: Int) = try {
        api.getOompaLoompaList(page)?.toDomain()
    } catch (ex: Exception) {
        null
    }

    suspend fun getDetail(id: Int) = try {
        api.getOompaLoompaDetail(id)?.toDomain()
    } catch (ex: Exception) {
        null
    }
}