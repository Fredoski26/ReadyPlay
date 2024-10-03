package com.example.readyplay.features.mycart


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemCartCell() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Blue
        ),

        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .background(Color.Black)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Card(
                shape = RoundedCornerShape(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 90.dp, height = 100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = com.example.readyplay.R.drawable.product_),
                        contentDescription = "Product Image",
                        modifier = Modifier.size(60.dp, 80.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }


            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Cars (Single Disc Edition)",
                    fontSize = 18.sp,
                    color = Color.Red
                )


                Text(
                    text = "Blu-Ray",
                    fontSize = 14.sp,
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp)
                )


                Text(
                    text = "₦6300",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(top = 20.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))


            Row(
                modifier = Modifier.align(Alignment.Bottom),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = com.example.readyplay.R.drawable.remove),
                    contentDescription = "Decrease Quantity",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {  }
                        .alpha(1f)
                )

                Spacer(modifier = Modifier.width(10.dp))


                Text(
                    text = "1",
                    fontSize = 20.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.width(10.dp))

                Image(
                    painter = painterResource(id = com.example.readyplay.R.drawable.add),
                    contentDescription = "Increase Quantity",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {  }
                        .alpha(1f)
                )
            }
        }
    }
}
