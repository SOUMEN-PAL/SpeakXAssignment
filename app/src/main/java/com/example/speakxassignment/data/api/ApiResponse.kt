package com.example.speakxassignment.data.api

import com.example.speakxassignment.data.model.Item

data class ApiResponse(
    val items :  List<Item>,
    val hasMore : Boolean
)