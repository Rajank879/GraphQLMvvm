package com.rajan.graphqlmvvm.mapper

import com.google.common.truth.Truth
import com.rajan.graphqlmvvm.GetCharactersQuery
import com.rajan.graphqlmvvm.data.mappers.mapToDomainModel
import com.rajan.graphqlmvvm.domain.models.EpisodeModel
import org.junit.Test

class EpisodeMapperTest {
    private val episode  = GetCharactersQuery.Episode(id = "12", name = "Pilot")

    private val episodeModel:EpisodeModel = episode.mapToDomainModel()

    @Test
    fun episodeModelMapperTest(){
        Truth.assertThat(episodeModel.id).isNotNull()
        Truth.assertThat(episodeModel.id).matches("12")
        Truth.assertThat(episodeModel.name).isNotNull()
        Truth.assertThat(episodeModel.name).matches("Pilot")
    }

}