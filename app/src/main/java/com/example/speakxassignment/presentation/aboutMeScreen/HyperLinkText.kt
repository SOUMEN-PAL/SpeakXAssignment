package com.example.speakxassignment.presentation.aboutMeScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.sp
import com.example.speakxassignment.R
import kotlin.text.append

@Composable
fun HyperlinkText(
    text: String,
    url: String,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    Text(
        buildAnnotatedString {
            val link =
                LinkAnnotation.Url(
                    url,
                    TextLinkStyles(
                        SpanStyle(
                            color = colorResource(R.color.appcolor),
                            fontSize = 16.sp
                        )
                    )
                ) {
                    val url = (it as LinkAnnotation.Url).url
                    uriHandler.openUri(url)
                }
            withLink(link) { append(text) }
        },
        modifier = modifier
    )
}