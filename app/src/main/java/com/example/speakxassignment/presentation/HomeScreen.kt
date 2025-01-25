package com.example.speakxassignment.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.speakxassignment.R
import com.example.speakxassignment.presentation.viewmodels.ItemViewModel
import com.example.speakxassignment.utils.ItemsState
import kotlinx.coroutines.delay


@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: ItemViewModel) {

    val itemState = viewModel.itemListState.collectAsState()
    val isDarkTheme = isSystemInDarkTheme()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            Column(
                modifier = Modifier.background(color = colorResource(R.color.appcolor))
            ) {
                TypewriterText(
                    text = "SpeakX Assignment"
                )
            }
        },
        containerColor = if (isDarkTheme) colorResource(R.color.darkBAck) else colorResource(R.color.lightBack)

    ) { it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when (itemState.value) {
                is ItemsState.Error -> {
                    Text("Error In Loading Data")
                }

                is ItemsState.Loading -> {
                    ShimmerComposable()
                }

                is ItemsState.Success -> {
                    val items =
                        (itemState.value as ItemsState.Success).data.collectAsLazyPagingItems()
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        items(
                            count = items.itemCount,
                            key = items.itemKey { item -> item.id }
                        ) { index ->
                            val item = items[index]

                            if (item != null) {
                                ItemInfo(item)
                            }

                        }

                        items.apply {
                            when {
                                loadState.append is LoadState.Loading -> item {
                                    ShimmerComposable()
                                }

                                loadState.prepend is LoadState.Loading -> item {
                                    ShimmerComposable()
                                }

                                loadState.refresh is LoadState.Error -> item {
                                    Text(
                                        "Error loading data",
                                        color = Color.Red,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            }
                        }


                    }
                }
            }


        }
    }


}

