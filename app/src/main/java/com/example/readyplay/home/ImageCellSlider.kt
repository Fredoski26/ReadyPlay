package com.example.readyplay.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.readyplay.R

@Composable
fun ImageCellSlider() {
    Card(
        modifier = Modifier
            .padding(horizontal = 94.dp)
            .wrapContentHeight(align = Alignment.CenterVertically)
            .fillMaxSize(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Image(
            painter = painterResource(id = R.drawable.product_),
            contentDescription = null,
            modifier = Modifier.width(width = 150.dp)
                .padding(horizontal = 8.dp, vertical = 25.dp)
                .height(height = 200.dp),
            contentScale = ContentScale.Fit
        )
    }
}
