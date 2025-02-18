package com.example.speakxassignment.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.speakxassignment.data.api.MockApi
import com.example.speakxassignment.data.model.Item

class ItemPagingSource(
    private val api: MockApi
) : PagingSource<Int, Item>() {

    private val initialKey = 200

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            state.closestPageToPosition(anchorPos)?.prevKey?.plus(10)
                ?: state.closestPageToPosition(anchorPos)?.nextKey?.minus(10)
        } ?: initialKey
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val currentKey = params.key ?: initialKey

        val direction = when (params) {
            is LoadParams.Prepend -> "up"
            is LoadParams.Append -> "down"
            is LoadParams.Refresh -> "up"
            else -> "up"
        }

        return try {
            val response = api.fetchItems(currentKey, direction)

            val prevKey = if (currentKey > 0) currentKey - 10 else null
            val nextKey = if (response.hasMore) currentKey + 10 else null

            LoadResult.Page(
                data = response.items,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
