package com.example.zenmoves

data class ActivityModel(
    val id: Int,
    val date: String,
    val exerciseType: String,
    val duration: Int,
    val calories: Int,
    val steps: Int
)
