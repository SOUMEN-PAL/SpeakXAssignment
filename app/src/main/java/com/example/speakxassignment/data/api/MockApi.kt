package com.example.speakxassignment.data.api

import com.example.speakxassignment.data.model.Item
import kotlinx.coroutines.delay

class MockApi {
    private val limit = 1500

    suspend fun fetchItems(
        id: Int,
        direction: String
    ): ApiResponse {
        delay(1000)
        val data = when (direction) {
            "up" -> {
                val start = (id - 10).coerceAtLeast(0)
                (start until id).map { Item(it, "Item $it") }
            }
            "down" -> {
                // Calculate end index without exceeding the limit.
                val end = (id + 10).coerceAtMost(limit)
                (id + 1..end).map { Item(it, "Item $it") }
            }
            else -> emptyList()
        }

        val hasMore = when (direction) {
            "up" -> id > 10
            "down" -> id < limit - 10
            else -> false
        }

        return ApiResponse(data, hasMore)
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