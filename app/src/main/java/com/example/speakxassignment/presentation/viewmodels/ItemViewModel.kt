package com.example.speakxassignment.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.speakxassignment.domain.ItemRepository
import com.example.speakxassignment.utils.ItemsState
import com.example.speakxassignment.utils.SearchItemState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {
    val isSearching = mutableStateOf(false)
    private val _itemsListState = MutableStateFlow<ItemsState>(ItemsState.Loading())
    val itemListState = _itemsListState.asStateFlow()

    private val _searchItemsListState = MutableStateFlow<SearchItemState>(SearchItemState.Loading())
    val searchItemState = _searchItemsListState.asStateFlow()

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            val data = repository.getItems().flow.cachedIn(viewModelScope)
            delay(2000)
            _itemsListState.value = ItemsState.Success(data)
        }
    }

    fun updateSearchQuery(query : String){
        _searchQuery.value = query
    }

    fun resetState(){
        _searchItemsListState.value = SearchItemState.Loading()
        _searchQuery.value = ""
    }

    var searchJob : Job? = null
    fun searchItem(){
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            val data = repository.searchItems(_searchQuery.value).flow.cachedIn(viewModelScope)
            _searchItemsListState.value = SearchItemState.Success(data)
        }
    }



}