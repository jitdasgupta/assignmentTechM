package com.jit.retrofit.coroutines.data.model

import com.google.gson.annotations.SerializedName

data class Movie(@SerializedName("Title")
                 var title: String?,
                 @SerializedName("Year")
                 var year: String?,
                 @SerializedName("imdbID")
                 var imdbID: String?,
                 @SerializedName("Type")
                 var type: String?,
                 @SerializedName("Poster")
                 var poster: String?
                 )
