package com.example.readyplay.dialogs


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.readyplay.R


@Composable
fun ScanScreen(showDialog: Boolean,
               onDismissRequest: () -> Unit,
               navController: NavHostController,
               ) {
    if (showDialog){
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(bottom = 40.dp)
                    //.shadow(4.dp)
                ) {
                    Text(
                        text = "What would you like to scan?",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(37.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.scan_),
                            contentDescription = "Bar Code",
                            modifier = Modifier
                                .size(130.dp)
                                .clickable {
                                  //  navController.navigate("Scan screen")

                                }
                                .padding(start = 20.dp)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.qr_code),
                            contentDescription = "QR Code",
                            modifier = Modifier
                                .size(130.dp)
                                .clickable {
                                    // navController.navigate("Scan screen")

                                }
                                .padding(end = 20.dp)
                        )
                    }

                    Text(
                        text = "Not Now",
                        color = Color.Red,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .clickable { onDismissRequest() }
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}


