package com.jit.retrofit.coroutines.data.repository

import com.jit.retrofit.coroutines.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()
    suspend fun getUserSearch(name:String) = apiHelper.getUserSearch(name)



}