package com.dnegu.loginpruebatecnica.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ButtonWhiteIcon(text: String, onclick: () -> Unit, icon: Int, contentDescription: String) {
    Button(
        onClick = onclick,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Gray),
        contentPadding = PaddingValues(16.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = contentDescription,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 16.dp, end = 24.dp)
                    .weight(1f)
                    .wrapContentSize(align = Alignment.Center)
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}
