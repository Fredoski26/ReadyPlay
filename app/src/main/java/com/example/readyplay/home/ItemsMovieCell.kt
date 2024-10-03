package com.example.readyplay.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.readyplay.R

@Composable
fun ItemMovieCard() {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .height(255.dp)
            .background(color = Color.Black)
            .padding(horizontal = 1.dp, vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(176.dp)
               /* .background(
                    painter = painterResource(id = R.drawable.item_bg),
                    contentScale = ContentScale.FillBounds
                )*/
        ) {
            Image(
                painter = painterResource(id = R.drawable.product_),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 85.dp, height = 120.dp)
                    .padding(10.dp)
                    .align(Alignment.Center)
                    .alpha(1f),
                contentScale = ContentScale.Fit
            )

            Box(
                modifier = Modifier
                    .size(16.dp)
                    //.background(painterResource(id = R.drawable.mak_group))
                    .padding(top = 10.dp, end = 20.dp)
                    .align(Alignment.TopEnd)
            )
        }

        Text(
            text = "N10,700",
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 5.dp, top = 8.dp)
        )

        Text(
            text = "Finding Nemo (Two-Disc Collector's Edition)",
            color = Color.Red,
            fontSize = 12.sp,
            maxLines = 2,
            modifier = Modifier
                .width(160.dp)
                .padding(top = 2.dp)
        )
    }
}
