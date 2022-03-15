package com.rajan.graphqlmvvm.data.datasource.remote

import com.google.gson.Gson
import com.rajan.graphqlmvvm.GetCharactersQuery
import com.rajan.graphqlmvvm.data.common.DataSourceException
import com.rajan.graphqlmvvm.data.common.RickAndMortyResult
import com.rajan.graphqlmvvm.helpers.getJson
import com.rajan.graphqlmvvm.utils.fromJsonToObjectType
import org.junit.Assert.*

class RemoteDataSourceImplTest : RemoteDataSource{
    override suspend fun getCharacters(page: Int): RickAndMortyResult<GetCharactersQuery.Characters?> {
        val result =
            Gson().fromJsonToObjectType<GetCharactersQuery.Data?>(getJson("list_characters.json"))

        return if (result!=null)
        {
            RickAndMortyResult.Success(result.characters)
        }
        else{
            RickAndMortyResult.Error(DataSourceException.Server(null))
        }
    }

}