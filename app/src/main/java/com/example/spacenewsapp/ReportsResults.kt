package com.example.spacenewsapp

data class ReportsResults(
    val id: Int,
    val title:String,
    val url:String,
    val image_url: String,
    val news_site: String,
    val summary: String,
    val published_at: String,
    val updated_at: String,
)
