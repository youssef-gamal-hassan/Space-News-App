package com.example.spacenewsapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageAPI {
    @GET("/planetary/apod/")
    fun getImage(@Query("api_key") apiKey: String, @Query("count") count: Int): Call<List<ImageResults>>
}