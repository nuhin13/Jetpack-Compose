package com.nuhin13.myapplication.ui.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface GraphQLService {

    //@Headers("Content-Type: application/json")
    @GET()
    suspend fun postDynamicQuery(@Body body: String): Response<String>
}