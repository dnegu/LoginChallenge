package com.dnegu.loginpruebatecnica.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BigTextScreen(text: String = ""){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Text(
        text = text,
        fontSize = 48.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight*25/100)
            .wrapContentSize(align = Alignment.BottomStart)
            .padding(top = 0.dp, start = 0.dp, end = 0.dp, bottom = 12.dp)
    )
}