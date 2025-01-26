package com.example.speakxassignment.presentation.searchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.speakxassignment.R
import com.example.speakxassignment.presentation.navigation.Screens

@Composable
fun SearchButton(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val context = LocalContext.current
    IconButton(
        onClick = {
            navController.navigate(Screens.searchScreen.route)
        },
        modifier = modifier
            .clip(CircleShape)
            .background(colorResource(R.color.searchIconColor))
            .size(70.dp)
    ) {
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Default.Search,
            contentDescription = "Search Icon",
            tint = Color.Black
        )
    }
}




