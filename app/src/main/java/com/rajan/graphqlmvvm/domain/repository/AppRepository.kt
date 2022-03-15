package com.rajan.graphqlmvvm.domain.repository

import com.rajan.graphqlmvvm.data.common.RickAndMortyResult
import com.rajan.graphqlmvvm.domain.models.CharactersModel
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    suspend fun getCharacters(page: Int): Flow<RickAndMortyResult<CharactersModel>>
}