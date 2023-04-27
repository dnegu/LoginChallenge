package com.dnegu.loginpruebatecnica.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SeparatorWithTextOptional(text: String = ""){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Divider(modifier = Modifier.weight(1f))
        if(text.isNotEmpty())
            Text(
                text = text,
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        Divider(modifier = Modifier.weight(1f))
    }
    Spacer(modifier = Modifier.height(16.dp))
}