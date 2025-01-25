package com.example.speakxassignment.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.speakxassignment.domain.ItemRepository
import com.example.speakxassignment.utils.ItemsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {
    private val _itemsListState = MutableStateFlow<ItemsState>(ItemsState.Loading())
    val itemListState = _itemsListState.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            val data = repository.getItems().flow.cachedIn(viewModelScope)
            _itemsListState.value = ItemsState.Success(data)
        }
    }

}