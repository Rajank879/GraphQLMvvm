package com.rajan.graphqlmvvm.mapper

import com.google.common.truth.Truth
import com.rajan.graphqlmvvm.GetCharactersQuery
import com.rajan.graphqlmvvm.data.mappers.mapToDomainModel
import com.rajan.graphqlmvvm.domain.models.InfoModel
import org.junit.Test

class InfoMapper {
    private val info = GetCharactersQuery.Info(pages = null, count = 4, next = 4)
    private val infoModel: InfoModel = info.mapToDomainModel()

    @Test
    fun infoModelMapperTest(){
        Truth.assertThat(infoModel.pages).isNotNull()
        Truth.assertThat(infoModel.next).isAtLeast(0)
        Truth.assertThat(infoModel.count).isGreaterThan(0)
    }
}