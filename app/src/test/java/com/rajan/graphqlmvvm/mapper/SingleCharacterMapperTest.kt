package com.rajan.graphqlmvvm.mapper

import com.google.common.truth.Truth
import com.rajan.graphqlmvvm.GetCharactersQuery
import com.rajan.graphqlmvvm.data.mappers.mapToDomainModel
import com.rajan.graphqlmvvm.domain.models.SingleCharacterModel
import org.junit.Test

class SingleCharacterMapperTest {
    private val result = GetCharactersQuery.Result(id = "", name = "Rajan" , image = "url", episode = emptyList())
    private val infoModel: SingleCharacterModel = result.mapToDomainModel()

    @Test
    fun singleCharacterModelMapperTest(){
        Truth.assertThat(infoModel.id).isNotNull()
        Truth.assertThat(infoModel.id).matches("")
        Truth.assertThat(infoModel.name).matches("Rajan")
        Truth.assertThat(infoModel.image).matches("url")
        Truth.assertThat(infoModel.episode).isEmpty()
    }
}