package com.jit.retrofit.coroutines.data.model

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("Search")
    val resultSearch: List<Movie>?
)
