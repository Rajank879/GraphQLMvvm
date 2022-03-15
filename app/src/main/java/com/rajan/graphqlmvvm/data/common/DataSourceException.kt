package com.rajan.graphqlmvvm.data.common

import java.lang.Error

sealed class DataSourceException(val messageResource: Any?):RuntimeException(){

    class UnExpected(messageResource: Any?):DataSourceException(messageResource)

    class Server(error: com.apollographql.apollo3.api.Error?) :DataSourceException(error)

}
