package com.rajan.graphqlmvvm.data.mappers

import com.rajan.graphqlmvvm.GetCharactersQuery
import com.rajan.graphqlmvvm.domain.models.CharactersModel
import com.rajan.graphqlmvvm.domain.models.EpisodeModel
import com.rajan.graphqlmvvm.domain.models.InfoModel
import com.rajan.graphqlmvvm.domain.models.SingleCharacterModel

fun GetCharactersQuery.Info.mapToDomainModel() =
    InfoModel(pages?:0, count ?:0, next ?:0)

fun GetCharactersQuery.Episode.mapToDomainModel() =
    EpisodeModel(id ?:"", name ?:"")

fun GetCharactersQuery.Result.mapToDomainModel() = SingleCharacterModel(
    id ?: "",
    name ?:"",
    image ?:"",
    episode ?.map { it!!.mapToDomainModel() }?: emptyList()
)

fun GetCharactersQuery.Characters.mapToDomainModel() = CharactersModel(
    info?.mapToDomainModel() ?: InfoModel(),
    results?.map { it!!.mapToDomainModel() }?: emptyList()
)