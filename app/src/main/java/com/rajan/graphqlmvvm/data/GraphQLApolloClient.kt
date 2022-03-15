package com.rajan.graphqlmvvm.data

import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.rajan.graphqlmvvm.GetCharactersQuery
import com.rajan.graphqlmvvm.data.common.BASE_URL

object GraphQLApolloClient {

    private fun apolloClient(): ApolloClient =
        ApolloClient.Builder().serverUrl(BASE_URL).build()

    fun getCharacters(page: Int): ApolloCall<GetCharactersQuery.Data> =
        apolloClient().query(GetCharactersQuery(Optional.presentIfNotNull(page)))
}