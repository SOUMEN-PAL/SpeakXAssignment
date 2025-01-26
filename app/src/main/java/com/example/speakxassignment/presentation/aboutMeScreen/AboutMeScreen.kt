package com.example.speakxassignment.presentation.aboutMeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.speakxassignment.R
import com.example.speakxassignment.presentation.TypewriterText
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutMeScreen(modifier: Modifier = Modifier, navController: NavController) {
    val isDarkTheme = isSystemInDarkTheme()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.appcolor)
                ),
                title = {
                    TypewriterText(
                        text = "Why Hire Me ?"
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
        containerColor = if (isDarkTheme) colorResource(R.color.darkBAck) else colorResource(R.color.lightBack)
    ) { innerPAdding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPAdding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                item {
                    Image(
                        painter = painterResource(R.drawable.myimage),
                        contentDescription = "My Image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .size(250.dp)
                    )
                }

                item {
                    Text(
                        text = "Hello! I'm Soumen Pal, a passionate Android developer. I hope you enjoyed exploring this app, which showcases my skills in Kotlin, Jetpack Compose, and Clean Architecture. I'm currently seeking new opportunities to contribute to innovative projects. If you're looking for a dedicated and skilled developer, I'd love to connect!",
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.linkedin),
                            contentDescription = "LinkedIn",
                            modifier = Modifier.size(50.dp)
                        )
                        Spacer(
                            modifier = Modifier.width(10.dp)
                        )

                        HyperlinkText(
                            text = "Have a look on my LinkedIn",
                            url = "https://www.linkedin.com/in/soumen-pal-a8598b245/",
                        )

                    }

                    Spacer(modifier = Modifier.height(18.dp))
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.githublogo),
                            contentDescription = "Github",
                            modifier = Modifier.size(50.dp)
                        )
                        Spacer(
                            modifier = Modifier.width(10.dp)
                        )

                        HyperlinkText(
                            text = "My Builds \uD83D\uDCAA\uD83C\uDFFB",
                            url = "https://github.com/SOUMEN-PAL",
                        )

                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.cv),
                            contentDescription = "CV",
                            modifier = Modifier.size(50.dp)
                        )
                        Spacer(
                            modifier = Modifier.width(10.dp)
                        )

                        HyperlinkText(
                            text = "Gosh \uD83D\uDE11 Still not Convinced\nHave my resume",
                            url = "https://drive.google.com/file/d/1JnSbI_HTNKBTh6LhgrLrSLjtqd4WWv19/view?usp=sharing",
                        )

                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    val drawable = ContextCompat.getDrawable(LocalContext.current , R.drawable.endanimation)
                    Image(
                        painter = rememberDrawablePainter(
                            drawable = drawable
                        ),
                        contentDescription = "Your GIF Description",
                        modifier = modifier
                    )
                    Text("Hire me please \uD83D\uDE4F\uD83C\uDFFC")
                }


            }


        }
    }
}