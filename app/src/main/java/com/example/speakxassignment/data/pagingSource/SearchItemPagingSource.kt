package com.example.speakxassignment.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.speakxassignment.data.api.MockApi
import com.example.speakxassignment.data.model.Item

class SearchItemPagingSource(
    private val mockApi: MockApi,
    private val query: String,
) : PagingSource<Int, Item>() {
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val id = params.key ?: 0
            val direction = if (id == 0) "down" else if (id > 0) "down" else "up"
            val response = mockApi.searchData(id, direction, query)
            val items = response.items
            val nextKey = if (response.hasMore) items.last().id + 1 else null
            val prevKey = if (id > 0) items.first().id - 1 else null
            LoadResult.Page(
                data = items,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}