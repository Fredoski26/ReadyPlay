package com.example.readyplay.features.scanner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.readyplay.R
import com.example.readyplay.dialogs.CheckoutSuccess

@Composable
fun Scanner(navController: NavHostController) {
    var showDialog by remember { mutableStateOf(false) }
    CheckoutSuccess(
        showDialog = showDialog,
        navController = navController,
        onDismissRequest = { showDialog = false }
    ) {
        Text("CheckoutSuccess")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(colorResource(R.color.readButton)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = "Back Button",
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                        .size(30.dp),

                    contentScale = ContentScale.Crop,
                    alignment = Alignment.CenterStart
                )


                Text(
                    text = "Scan QR Code",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )


                Image(
                    painter = painterResource(id = R.drawable.shopping_cart),
                    contentDescription = "Cart Icon",
                    modifier = Modifier
                        .size(30.dp),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.CenterEnd
                )
            }
        }

        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center) {

            Text(
                modifier = Modifier.clickable {
                    showDialog = true
                  //  navController.navigate("MovieDetails screen/${movieList[itemIndex].id}")

                },
                text = "scanned",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewScanner() {
    val navController = rememberNavController()
   // Scanner(navController = navController)
}
