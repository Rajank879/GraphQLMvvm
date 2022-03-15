package com.rajan.graphqlmvvm.data.repository

import com.rajan.graphqlmvvm.data.common.RickAndMortyResult
import com.rajan.graphqlmvvm.data.datasource.local.AppDao
import com.rajan.graphqlmvvm.data.datasource.remote.RemoteDataSource
import com.rajan.graphqlmvvm.data.mappers.mapToDomainModel
import com.rajan.graphqlmvvm.domain.models.CharactersModel
import com.rajan.graphqlmvvm.domain.models.InfoModel
import com.rajan.graphqlmvvm.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class AppRepositoryImpl constructor(
    private val remoteDataSource: RemoteDataSource,
    private val appDao: AppDao
):AppRepository {
    override suspend fun getCharacters(page: Int): Flow<RickAndMortyResult<CharactersModel>> =
    flow {
        when(val result = remoteDataSource.getCharacters(page)){
            is RickAndMortyResult.Success->{

                result.data?.let {
                    it.mapToDomainModel().apply {
                        appDao.savedListCharacters(results)
                        emit(RickAndMortyResult.Success(this))
                    }
                }

            }
            is RickAndMortyResult.Error ->{
                val listCharacters = appDao.getListCharacters()
                if (listCharacters.isNotEmpty())
                {
                    emit(
                        RickAndMortyResult.Success(
                            CharactersModel(
                                InfoModel(),
                                listCharacters
                            )
                        )
                    )
                } else {
                    emit(RickAndMortyResult.Error(result.exception))
                }
            }
        }
    }.onStart { emit(RickAndMortyResult.Loading) }
}