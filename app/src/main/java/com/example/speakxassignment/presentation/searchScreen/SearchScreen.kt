package com.example.speakxassignment.presentation.searchScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.speakxassignment.R
import com.example.speakxassignment.presentation.ItemInfo
import com.example.speakxassignment.presentation.ShimmerComposable
import com.example.speakxassignment.presentation.TypewriterText
import com.example.speakxassignment.presentation.viewmodels.ItemViewModel
import com.example.speakxassignment.utils.SearchItemState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ItemViewModel
) {
    val isDarkTheme = isSystemInDarkTheme()
    val searchedDataState = viewModel.searchItemState.collectAsState()
    var searchQuery by viewModel.searchQuery
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var isFocused by rememberSaveable { mutableStateOf(viewModel.isSearching.value) }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.appcolor)
                ),
                title = {
                    TypewriterText(
                        text = "SpeakX Assignment"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back Button",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    modifier = Modifier.size(88.dp),
                    onClick = {

                    }
                ) {
                    Image(
                        painter = painterResource(R.drawable.developer),
                        contentDescription = "Know About the Developer",
                        contentScale = ContentScale.Fit,
                    )

                }
                Text(
                    text = "About\nDeveloper",
                    textAlign = TextAlign.Center
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        containerColor = if (isDarkTheme) colorResource(R.color.darkBAck) else colorResource(R.color.lightBack)
    ) { it ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth(),

                ) {
                //Search Bar
                OutlinedTextField(
                    value = searchQuery,
                    singleLine = true,
                    onValueChange = { search ->
                        searchQuery = search
                        viewModel.searchQuery.value = searchQuery
                        viewModel.searchItem()

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused
                            if (isFocused) {
                                viewModel.isSearching.value = true
                            }
                        }
                        .weight(2f),
                    trailingIcon = {
                        IconButton(onClick = {
                            if (isFocused) {
                                viewModel.updateSearchQuery("")
                                searchQuery = ""
                                viewModel.resetState()
                                viewModel.isSearching.value = false
                                focusManager.clearFocus()
                            } else {
                                viewModel.isSearching.value = true
                                focusRequester.requestFocus()
                            }
                        }) {
                            if (isFocused) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close",
                                    modifier = Modifier.size(28.dp)
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search",
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                        }
                    },
                    label = {
                        Text(
                            text = "Search Item",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = if (isDarkTheme) colorResource(R.color.darkBAck) else colorResource(
                            R.color.searchBarColor
                        ),
                        unfocusedBorderColor = if (isDarkTheme) colorResource(R.color.itemColor) else colorResource(
                            R.color.appcolor
                        ),
                        unfocusedLabelColor = if (isDarkTheme) colorResource(R.color.itemColor) else colorResource(
                            R.color.appcolor
                        ),
                        unfocusedTrailingIconColor = if (isDarkTheme) colorResource(R.color.itemColor) else colorResource(
                            R.color.appcolor
                        ),
                        focusedContainerColor = if (isDarkTheme) colorResource(R.color.darkBAck) else colorResource(
                            R.color.searchBarColor
                        ),
                        focusedBorderColor = if (isDarkTheme) colorResource(R.color.appcolor) else colorResource(
                            R.color.itemColor
                        ),
                        focusedLabelColor = if (isDarkTheme) colorResource(R.color.appcolor) else colorResource(
                            R.color.itemColor
                        ),
                        focusedTrailingIconColor = if (isDarkTheme) colorResource(R.color.appcolor) else colorResource(
                            R.color.itemColor
                        ),
                        cursorColor = if (isDarkTheme) colorResource(R.color.dividerColor) else colorResource(
                            R.color.darkBAck
                        )
                    )
                )
            }
            LaunchedEffect(viewModel.isSearching.value) {
                if (viewModel.isSearching.value) {
                    focusRequester.requestFocus()
                }
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (searchedDataState.value) {
                    is SearchItemState.Error -> {
                        Text("Error")
                    }

                    is SearchItemState.Loading -> {
                        CircularProgressIndicator(
                            color = if (isDarkTheme) colorResource(R.color.itemColor) else colorResource(
                                R.color.appcolor
                            )
                        )
                        Text(
                            text = "Try to Search Something",
                            color = if (isDarkTheme) colorResource(R.color.dividerColor) else colorResource(
                                R.color.darkBAck
                            )
                        )
                    }

                    is SearchItemState.Success -> {
                        val data =
                            (searchedDataState.value as SearchItemState.Success).data.collectAsLazyPagingItems()
                        if (data.itemCount != 0) {


                            LazyColumn {
                                items(data.itemCount) { index ->
                                    val item = data[index]
                                    if (item != null) {
                                        ItemInfo(item)
                                    }

                                }
                                data.apply {
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


                        } else {
                            Text("Search something like Item 1 or 12", color = Color.Red)
                        }
                    }
                }
            }
        }
    }


}