package com.example.spacenewsapp

data class ReportsReturns(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ReportsResults>
)
