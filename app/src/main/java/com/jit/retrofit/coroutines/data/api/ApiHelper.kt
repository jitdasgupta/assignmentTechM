package com.jit.retrofit.coroutines.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getUsers() = apiService.getUsers()
    suspend fun getUserSearch(name:String) = apiService.getUserSearch(name)
}