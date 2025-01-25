package com.example.speakxassignment.data.api

import com.example.speakxassignment.data.model.Item
import kotlinx.coroutines.delay

class MockApi {
    private val limit = 1500

    suspend fun fetchItems(
        id : Int,
        direction : String
    ):ApiResponse{
        delay(1000)
        val data = when (direction){
            "up" -> (id - 10 until id).filter { it >= 0 }.map { Item(it , "Item $it") }
            "down" -> (id + 1 .. id+10).filter { it<=limit }.map { Item(it , "Item $it") }
            else -> emptyList<Item>()
        }

        val hasMore = data.isNotEmpty()
        val response = ApiResponse(data , hasMore)
        return response
    }
}