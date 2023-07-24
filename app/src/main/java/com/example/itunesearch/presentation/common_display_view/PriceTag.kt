package com.example.itunesearch.presentation.common_display_view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itunesearch.presentation.theme.TagTextColor
import com.example.itunesearch.presentation.theme.CustomPurple

@Composable
fun PriceTag(price: Double, fontSize: TextUnit = 12.sp) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp, color = CustomPurple, shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = "$price $",
            color = TagTextColor,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = fontSize
            ),
        )
    }
}