package com.example.readyplay.features.mycart


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.readyplay.R
import com.example.readyplay.dialogs.CheckoutSuccess
import com.example.readyplay.ui.theme.ReadyPlayTheme
import com.example.readyplay.viewmodel.MovieViewModel



@SuppressLint("Range")
@Composable
fun MyCart(id: Int, navController: NavHostController) {
    val movieViewModel = viewModel<MovieViewModel>()
    movieViewModel.id = id
    movieViewModel.getDetailsById()
    val state = movieViewModel.state

    var showDialog by remember { mutableStateOf(false) }
    CheckoutSuccess(
        showDialog = showDialog,
        navController = navController,
        onDismissRequest = { showDialog = false }
    ) {
        Text("CheckoutSuccess")
    }

    val details = state.detailsData
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 28.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = "Back",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )

                Spacer(modifier = Modifier.width(25.dp))
                Text(
                    text = "My Cart",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 70.dp)
                        .align(Alignment.CenterVertically)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                contentPadding = PaddingValues(1.dp),
                content = {
                    items(state.movies.size) {
                        if (it >= state.movies.size - 1 && !state.endReached && !state.isLoading) {
                            movieViewModel.loadNextItems()
                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Black)
                                .padding(horizontal = 8.dp),

                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Box(modifier = Modifier.background(Color.Black)) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Column(
                                        modifier = Modifier
                                            .size(width = 90.dp, height = 100.dp)
                                            .background(color = Color.White, shape = RoundedCornerShape(4.dp)),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        AsyncImage(
                                            model = state.movies[it].poster,
                                            contentDescription = "",
                                            modifier = Modifier.width(width = 150.dp)
                                                .padding(horizontal = 8.dp, vertical = 25.dp)
                                                .height(height = 200.dp),
                                            contentScale = ContentScale.Fit
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(16.dp))

                                    Column(
                                        modifier = Modifier.weight(1f)
                                    ) {

                                        Text(
                                            text = state.movies[it].title,
                                            fontSize = 12.sp,
                                            maxLines = 1,
                                            color = colorResource(R.color.movieColor)
                                        )

                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = "Blu-Ray",
                                            color = Color.White
                                        )
                                        Spacer(modifier = Modifier.height(20.dp))
                                        Text(
                                            text = "₦6300",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                    }


                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.align(Alignment.Bottom)
                                    ) {
                                        IconButton(onClick = { }) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.remove),
                                                contentDescription = null,
                                                tint = Color.White,
                                                modifier = Modifier
                                                    .size(30.dp)
                                                    .background(Color.Gray, shape = RoundedCornerShape(4.dp))
                                            )
                                        }

                                        Text(
                                            text = "1",
                                            fontSize = 20.sp,
                                            color = Color.White,
                                            modifier = Modifier.padding(horizontal = 10.dp)
                                        )

                                        IconButton(onClick = {  }) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.add),
                                                contentDescription = null,
                                                tint = Color.White,
                                                modifier = Modifier
                                                    .size(30.dp)
                                                    .background(Color.Gray, shape = RoundedCornerShape(4.dp))
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            )

        }

        Column(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .align(alignment = Alignment.BottomCenter)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 20.dp)
                ) {
                    Text(
                        text = "Price",
                        fontSize = 14.sp,
                        color = Color.Black,
                    )
                    Text(
                        text = "N12,600",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight(500),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Button(
                    onClick = {
                    showDialog = true
                    },
                    modifier = Modifier
                        .width(150.dp)
                        .height(54.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.readButton)),
                    contentPadding = PaddingValues(15.dp)
                ) {
                    Text(text = "Checkout", color = Color.White)
                }
            }
        }

    }

}

@Composable
fun Checkout(){
    Box(modifier = Modifier) {
        Column(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .height(80.dp)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 20.dp)
                ) {
                    Text(
                        text = "Price",
                        fontSize = 14.sp,
                        color = Color.Black,
                    )
                    Text(
                        text = "N12,600",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight(500),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Button(
                    onClick = { /* Handle checkout click */ },
                    modifier = Modifier
                        .width(150.dp)
                        .height(54.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.readButton)),
                    contentPadding = PaddingValues(15.dp)
                ) {
                    Text(text = "Checkout", color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyCartScreen() {
    val navController = rememberNavController()
    ReadyPlayTheme {
       // MyCart(id = 2, navController)
    }
}
