package com.rajan.graphqlmvvm.di

import com.rajan.graphqlmvvm.data.datasource.local.AppDao
import com.rajan.graphqlmvvm.data.datasource.remote.RemoteDataSourceImpl
import com.rajan.graphqlmvvm.data.repository.AppRepositoryImpl
import com.rajan.graphqlmvvm.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAppRepository(appDao: AppDao):AppRepository{
        val remoteDataSourceImpl = RemoteDataSourceImpl()
        return AppRepositoryImpl(remoteDataSourceImpl, appDao)
    }
}