package com.example.speakxassignment.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.speakxassignment.data.api.MockApi
import com.example.speakxassignment.data.model.Item

class ItemPagingSource(
    private val api : MockApi
): PagingSource<Int , Item>() {
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try{
            val currentId = params.key ?: 0
            val direction = if(params.loadSize > 0) "down" else "up"

            val apiResponse = api.fetchItems(currentId , direction)

            val prevKey = if(direction == "up" && currentId > 0) currentId - 10 else null
            val nextKey = if(apiResponse.hasMore) currentId + 10 else null

            LoadResult.Page(
                data = apiResponse.items,
                prevKey = prevKey,
                nextKey = nextKey
            )

        }catch (e: Exception){
            LoadResult.Error(e)
        }

    }

}