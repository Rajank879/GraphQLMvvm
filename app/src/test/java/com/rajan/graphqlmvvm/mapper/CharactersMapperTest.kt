package com.rajan.graphqlmvvm.mapper

import com.google.common.truth.Truth
import com.rajan.graphqlmvvm.GetCharactersQuery
import com.rajan.graphqlmvvm.data.mappers.mapToDomainModel
import com.rajan.graphqlmvvm.domain.models.CharactersModel
import org.junit.Test

class CharactersMapperTest {
    private val info = GetCharactersQuery.Info(pages = null, count = 4, next = 4)

    private val result  =
        GetCharactersQuery.Result(id = "", name = "", image = "", episode = emptyList())

    private val characters = GetCharactersQuery.Characters(info = info, results = listOf(result))
    private val infoModel:CharactersModel =characters.mapToDomainModel()

    @Test
    fun charactersModelMappersTest(){
        Truth.assertThat(infoModel.info).isNotNull()
        Truth.assertThat(infoModel.results.size).isAtLeast(0)
        Truth.assertThat(infoModel.results[0].name).matches("")
    }
}