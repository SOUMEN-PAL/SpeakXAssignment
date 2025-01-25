package com.example.speakxassignment.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.speakxassignment.data.api.MockApi
import com.example.speakxassignment.data.model.Item
import com.example.speakxassignment.data.pagingSource.ItemPagingSource

class ItemRepository(
    private val api : MockApi
) {
    fun getItems(): Pager<Int , Item>{
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ItemPagingSource(api) }
        )
    }
}