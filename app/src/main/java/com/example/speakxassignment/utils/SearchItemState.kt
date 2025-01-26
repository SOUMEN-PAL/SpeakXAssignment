package com.example.speakxassignment.utils

import androidx.paging.PagingData
import com.example.speakxassignment.data.model.Item
import kotlinx.coroutines.flow.Flow

sealed class SearchItemState {
    class Loading() : SearchItemState()
    class Error() : SearchItemState()
    class Success(val data: Flow<PagingData<Item>>):SearchItemState()
}