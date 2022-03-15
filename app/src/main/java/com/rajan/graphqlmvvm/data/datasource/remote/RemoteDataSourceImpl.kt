package com.rajan.graphqlmvvm.data.datasource.remote

import com.rajan.graphqlmvvm.GetCharactersQuery
import com.rajan.graphqlmvvm.data.GraphQLApolloClient
import com.rajan.graphqlmvvm.data.common.DataSourceException
import com.rajan.graphqlmvvm.data.common.RickAndMortyResult

class RemoteDataSourceImpl:RemoteDataSource {
    override suspend fun getCharacters(page: Int): RickAndMortyResult<GetCharactersQuery.Characters> {
        return try {
            val result = GraphQLApolloClient.getCharacters(page).execute()
            if (result.hasErrors()){
                RickAndMortyResult.Error(DataSourceException.Server(result.errors?.first()))
            }
            else{
                RickAndMortyResult.Success(result.data?.characters!!)
            }
        } catch (e : Exception)
        {
            RickAndMortyResult.Error(DataSourceException.UnExpected("Something went wrong"))
        }
    }
}