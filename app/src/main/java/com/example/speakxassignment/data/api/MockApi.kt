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

    suspend fun searchData(
        id: Int,
        direction: String,
        query: String
    ): ApiResponse {
        delay(1000) // Simulate network delay

        // Generate a larger list of items for searching
        val allItems = (0..limit).map { Item(it, "Item $it") }

        // Filter items based on the query
        val filteredItems = allItems.filter { item ->
            item.title.contains(query, ignoreCase = true)
        }

        // Simulate pagination based on direction
        val paginatedItems = when (direction) {
            "up" -> filteredItems.filter { it.id < id }.takeLast(10)
            "down" -> filteredItems.filter { it.id > id }.take(10)
            else -> emptyList()
        }

        val hasMore = paginatedItems.size >= 10
        return ApiResponse(paginatedItems, hasMore)
    }
}