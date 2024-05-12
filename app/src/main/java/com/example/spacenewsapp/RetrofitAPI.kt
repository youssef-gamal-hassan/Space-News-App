package com.example.spacenewsapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import kotlin.contracts.Returns

interface RetrofitAPI {
    @GET("/v4/articles/")
    fun getArticles(): Call<com.example.spacenewsapp.Returns>

    @GET("/v4/articles/")
    fun getArticles(@Query("search") searchTerms:String): Call<com.example.spacenewsapp.Returns>


    @GET("/v4/blogs/")
    fun getBlogs(): Call<com.example.spacenewsapp.Returns>

    @GET("/v4/reports/")
    fun getReports(): Call<ReportsReturns>



}