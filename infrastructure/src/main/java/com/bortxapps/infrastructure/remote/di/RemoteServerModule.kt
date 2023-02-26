package com.bortxapps.infrastructure.remote.di

import com.bortxapps.infrastructure.remote.service.OompaLoompaInfoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteServerModule {
    @Provides
    @Singleton
    fun getApi() = OompaLoompaInfoService.api
}