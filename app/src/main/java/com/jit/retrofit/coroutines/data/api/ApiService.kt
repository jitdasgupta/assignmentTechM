package com.jit.retrofit.coroutines.data.api

import com.jit.retrofit.coroutines.data.model.Search
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?apikey=b9bd48a6&s=Marvel&type=movie")
    suspend fun getUsers(): Search

    @GET("/")
    suspend fun getUserSearch(@Query("s") searchText: String, @Query("apikey") ombd_api_key: String = "b9bd48a6"): Search

}