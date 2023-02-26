package com.bortxapps.infrastructure.remote.api

import com.bortxapps.infrastructure.remote.data.OompaLoompaInfoListRemote
import com.bortxapps.infrastructure.remote.data.OompaLoompaInfoRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OompaLoompaInfoApi {
    @GET("oompa-loompas/")
    suspend fun getOompaLoompaList(@Query("page") page: Int): OompaLoompaInfoListRemote?

    @GET("oompa-loompas/{id}")
    suspend fun getOompaLoompaDetail(@Path("id") id: Int): OompaLoompaInfoRemote?
}