package com.nuhin13.myapplication.ui.retrofit

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object GraphQLInstance {

    private const val BASE_URL: String = "https://devapiv2.walcart.com/graphql/"

    val graphQLService: GraphQLService by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build().create(GraphQLService::class.java)
    }
}