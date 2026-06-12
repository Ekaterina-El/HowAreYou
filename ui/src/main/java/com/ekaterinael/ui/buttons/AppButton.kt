package com.ekaterinael.ui.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    containerColor: Color = Color.White,
    textColor: Color = Color.Black
) {
    Button(
        modifier = modifier.fillMaxWidth().heightIn(min = 64.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 5.dp,
            pressedElevation = 1.dp
        ),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = textColor
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}