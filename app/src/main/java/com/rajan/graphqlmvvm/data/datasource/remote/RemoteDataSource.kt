package com.rajan.graphqlmvvm.data.datasource.remote

import com.rajan.graphqlmvvm.GetCharactersQuery
import com.rajan.graphqlmvvm.data.common.RickAndMortyResult

interface RemoteDataSource {
    suspend fun getCharacters(page: Int): RickAndMortyResult<GetCharactersQuery.Characters?>
}

//gradlew.bat :app:downloadApolloSchema --endpoint=https://rickandmortyapi.com/graphql/ --schema=app/src/main/graphql/com/rajan/graphqlmvvm/schema.sdl
