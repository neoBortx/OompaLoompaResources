package com.bortxapps.infrastructure.database.di

import android.content.Context
import androidx.room.Room
import com.bortxapps.infrastructure.database.dao.OompaLoompaInfoDao
import com.bortxapps.infrastructure.database.database.OompaLoompaResourcesDataBase
import com.bortxapps.infrastructure.database.database.OompaLoompaResourcesDataBase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        OompaLoompaResourcesDataBase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideElectionDao(appDatabase: OompaLoompaResourcesDataBase): OompaLoompaInfoDao {
        return appDatabase.oompaLoompaDao()
    }
}