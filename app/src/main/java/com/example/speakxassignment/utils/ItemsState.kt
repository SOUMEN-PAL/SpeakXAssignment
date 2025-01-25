package com.example.speakxassignment.utils

import androidx.paging.PagingData
import com.example.speakxassignment.data.model.Item
import kotlinx.coroutines.flow.Flow

sealed class ItemsState {
    class Loading() : ItemsState()
    class Error(val errorMessage: String): ItemsState()
    class Success(val data: Flow<PagingData<Item>>) : ItemsState()
}