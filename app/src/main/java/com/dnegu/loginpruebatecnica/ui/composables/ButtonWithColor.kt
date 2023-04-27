package com.dnegu.loginpruebatecnica.ui.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ButtonWithColor(text: String, onclick: () -> Unit, color: Long = 0xFF01CD90){
    Button(
        onClick = onclick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(color)),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text = text, color = Color.White)
    }
    Spacer(modifier = Modifier.height(16.dp))
}
