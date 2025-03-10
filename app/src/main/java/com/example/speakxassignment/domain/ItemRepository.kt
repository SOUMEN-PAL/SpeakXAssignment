package com.example.speakxassignment.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.speakxassignment.data.api.ApiResponse
import com.example.speakxassignment.data.api.MockApi
import com.example.speakxassignment.data.model.Item
import com.example.speakxassignment.data.pagingSource.ItemPagingSource
import com.example.speakxassignment.data.pagingSource.SearchItemPagingSource
import kotlinx.coroutines.flow.Flow

class ItemRepository(
    private val api : MockApi
) {
    fun getItems(): Pager<Int, Item> {
        return Pager(
            initialKey = 200,
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ItemPagingSource(api) }
        )
    }

    fun searchItems(query: String): Pager<Int, Item> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = { SearchItemPagingSource(api, query) }
        )
    }

}