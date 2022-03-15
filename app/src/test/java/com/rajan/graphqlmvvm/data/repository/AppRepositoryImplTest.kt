package com.rajan.graphqlmvvm.data.repository

import com.rajan.graphqlmvvm.data.common.RickAndMortyResult
import com.rajan.graphqlmvvm.data.datasource.remote.RemoteDataSourceImplTest
import com.rajan.graphqlmvvm.data.mappers.mapToDomainModel
import com.rajan.graphqlmvvm.domain.models.CharactersModel
import com.rajan.graphqlmvvm.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Assert.*

class AppRepositoryImplTest(private val dataSourceImplTest: RemoteDataSourceImplTest):
        AppRepository{
    override suspend fun getCharacters(page: Int): Flow<RickAndMortyResult<CharactersModel>> {
        return flow {
            dataSourceImplTest.getCharacters(page).run {
                when(this){
                    is RickAndMortyResult.Success->{
                        data?.let {
                            emit(RickAndMortyResult.Success(it.mapToDomainModel()))
                        }
                    }
                    is RickAndMortyResult.Error->{
                        emit(RickAndMortyResult.Error(exception))
                    }
                }
            }
        }
    }

}