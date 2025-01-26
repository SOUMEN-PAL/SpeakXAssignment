package com.example.speakxassignment.presentation


import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration


import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speakxassignment.R
import com.example.speakxassignment.data.model.Item
import kotlinx.coroutines.delay


//Compossable without shimmer

@Composable
fun ItemInfo(data: Item) {

    var showShimmer by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = true) {
        delay(500) // 0.5 seconds
        showShimmer = false
    }

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val isDarkTheme = isSystemInDarkTheme()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .border(
                    width = 2.dp,
                    color = colorResource(R.color.shapeColorBorder),
                    shape = CircleShape
                )
                .clip(CircleShape)
                .background(color = colorResource(R.color.shapeColor)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = data.id.toString(),
                fontSize = 28.sp,
                color = colorResource(R.color.idColor),
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        width = 1.dp,
                        color = if (!isDarkTheme) colorResource(R.color.itemColor) else colorResource(
                            R.color.itemBOrder
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .background(colorResource(R.color.itemColor))
                    .padding(vertical = 14.dp, horizontal = if (isLandscape) 180.dp else 60.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = data.title,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textDecoration = TextDecoration.Underline
                )
            }
        }

    }
}


