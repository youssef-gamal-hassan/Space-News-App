package com.example.spacenewsapp

data class Returns(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Results>
)
