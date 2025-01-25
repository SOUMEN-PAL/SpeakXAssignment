package com.example.speakxassignment.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.speakxassignment.data.model.Item
import com.example.speakxassignment.presentation.viewmodels.ItemViewModel
import com.example.speakxassignment.utils.ItemsState

@Composable
fun HomeScreen(modifier: Modifier = Modifier , viewModel: ItemViewModel) {

    val itemState = viewModel.itemListState.collectAsState()




    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when(itemState.value){
            is ItemsState.Error -> {

            }
            is ItemsState.Loading -> {
                CircularProgressIndicator()
            }
            is ItemsState.Success -> {
                val items = (itemState.value as ItemsState.Success).data.collectAsLazyPagingItems()
                LazyColumn(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ){

                    items(items.itemCount){index->
                        val item = items[index]

                        if(item != null){
                            ItemInfo(item)
                        }

                    }

                }
            }
        }



    }

}

@Composable
fun ItemInfo(data: Item){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = data.title , fontSize = 34.sp)
    }
}