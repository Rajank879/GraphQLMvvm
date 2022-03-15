package com.rajan.graphqlmvvm.di

import android.content.Context
import com.rajan.graphqlmvvm.data.datasource.local.AppDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) =
        AppDb.getDataBase(context)


    @Provides
    fun provideAppDAO(db:AppDb) = db.appDao()
}