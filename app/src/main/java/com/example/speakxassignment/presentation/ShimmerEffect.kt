package com.example.speakxassignment.presentation

import android.content.res.Configuration
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speakxassignment.R
import kotlinx.coroutines.delay

fun Modifier.shimmerEffect(): Modifier = composed {
    var showShimmer by remember { mutableStateOf(true) }
    val startOffsetX by animateFloatAsState(
        targetValue = if (showShimmer) 1f else 0f,
        animationSpec = tween(
            durationMillis = 500,
            easing = FastOutSlowInEasing
        ), label = "ShimmerOffsetX"
    )
    LaunchedEffect(key1 = true) {
        delay(1000)
        showShimmer = false
    }

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5),
            ),
            start = Offset(-200f * startOffsetX, 0f),
            end = Offset(200f * startOffsetX, 0f)
        )
    )
}

@Composable
fun ShimmerComposable(modifier: Modifier = Modifier) {
    var showShimmer by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = true) {
        delay(500) // 1 seconds
        showShimmer = false
    }

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val isDarkTheme = isSystemInDarkTheme()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(16.dp)
            .shadow(2.dp , shape = RoundedCornerShape(24.dp))
            .background(color = colorResource(R.color.dividerColor) , shape = RoundedCornerShape(24.dp))
            .shimmerEffect(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .padding(6.dp)
                .border(
                    width = 2.dp,
                    color = colorResource(R.color.shapeColorBorder),
                    shape = CircleShape
                )
                .clip(CircleShape)
                .background(color = colorResource(R.color.shapeColor))
                .shimmerEffect(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "",
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
                    .shimmerEffect()
                    .size(height = 55.dp, width = if (isLandscape) 480.dp else 180.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = "",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun ShimmerCompossablePReview(modifier: Modifier = Modifier) {
    ShimmerComposable()
}