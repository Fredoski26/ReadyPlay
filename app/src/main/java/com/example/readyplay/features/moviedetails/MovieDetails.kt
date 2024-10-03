package com.example.readyplay.features.moviedetails

import AddToCartScreen
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.readyplay.R
import com.example.readyplay.dialogs.ScanScreen
import com.example.readyplay.model.Data
import com.example.readyplay.model.MovieDetail
import com.example.readyplay.ui.theme.ReadyPlayTheme
import com.example.readyplay.viewmodel.MovieViewModel

@Composable
fun MovieDetailsScreen(id: Int, navController: NavHostController) {
    val movieViewModel = viewModel<MovieViewModel>()
    movieViewModel.id = id
    movieViewModel.getDetailsById()
    val state = movieViewModel.state

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
                .padding(horizontal = 28.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
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

            Icon(
                painter = painterResource(id = R.drawable.shopping_cart),
                contentDescription = "Cart",
                tint = Color.Unspecified,
                modifier = Modifier.size(30.dp)
            )
        }

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .width(width = 1.dp)
                .verticalScroll(scrollState)
                .padding(horizontal = 28.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(16.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
            ) {

                AsyncImage(
                    model = details.poster,
                    contentDescription = "",
                    modifier = Modifier.width(width = 90.dp)
                        .padding(horizontal = 8.dp, vertical = 25.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                        .height(height = 100.dp),
                    contentScale = ContentScale.Fit
                )




            }


            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = details.genres.toString(),
                color = Color.White,
                fontSize = 12.sp
            )

            Text(
                text = details.title,
                color = colorResource(R.color.movieColor),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 20.dp)
            )

            Text(
                text = "Rated: ${details.rated}",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 20.dp)
            )

            Text(
                text = details.plot,
                color = Color.White,
                fontSize = 9.sp,
                maxLines = 2,
                letterSpacing = TextUnit.Unspecified,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Format",
                color = colorResource(R.color.readButton),
                fontSize = 12.sp
            )

            FormatOptions()

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Quantity",
                color = colorResource(R.color.readButton),
                fontSize = 18.sp
            )

            QuantitySelector()
        }


        Spacer(modifier = Modifier.height(50.dp))


    }

    Column(modifier = Modifier
        .height(80.dp)
        .fillMaxWidth()
        .align(alignment = Alignment.BottomCenter)
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        CheckOut(id = id, movieList = state.movies,
            navController = navController)
    }
}

}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 28.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = "Back",
            tint = Color.Unspecified,
            modifier = Modifier.size(40.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.shopping_cart),
            contentDescription = "Cart",
            tint = Color.Unspecified,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun MovieContent(details: MovieDetail) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .width(width = 1.dp)
            .verticalScroll(scrollState)
            .padding(horizontal = 28.dp)
    ) {
        // ViewPager2 equivalent (use a carousel component in Compose)
        Card(
            shape = RoundedCornerShape(24.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MaterialTheme.colorScheme.surface)
            ) {
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
                    AsyncImage(
                        model = details.poster,
                        contentDescription = "",
                        modifier = Modifier.width(width = 150.dp)
                            .padding(horizontal = 8.dp, vertical = 25.dp)
                            .height(height = 200.dp),
                        contentScale = ContentScale.Fit
                    )
                }

            }
        }


        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = details.genres.toString(),
            color = Color.White,
            fontSize = 12.sp
        )

        Text(
            text = details.title,
            color = colorResource(R.color.movieColor),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp)
        )

        Text(
            text = "Rated: ${details.rated}",
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 20.dp)
        )

        Text(
            text = details.plot,
            color = Color.White,
            fontSize = 9.sp,
            maxLines = 2,
            letterSpacing = TextUnit.Unspecified,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Format",
            color = colorResource(R.color.readButton),
            fontSize = 12.sp
        )

        FormatOptions()

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Quantity",
            color = colorResource(R.color.readButton),
            fontSize = 18.sp
        )

        QuantitySelector()
    }
}

@Composable
fun FormatOptions() {
    Row(modifier = Modifier.padding(top = 10.dp)) {
        FormatOption(text = "Blu-Ray\n₦6300")
        FormatOption(text = "DVD\n₦8700")
        FormatOption(text = "4K\n₦12400")
    }
}

@Composable
fun FormatOption(text: String) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .size(80.dp, 60.dp)
            .padding(end = 8.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        )
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
            Text(text = text, fontSize = 12.sp, color = Color.Black)
        }
    }
}

@Composable
fun QuantitySelector() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 10.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.remove),
            contentDescription = "Decrease",
            tint = Color.Unspecified,
            modifier = Modifier
                .size(40.dp)
                .background(Color.Black)
        )

        Text(
            text = "1",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.add),
            contentDescription = "Increase",
            tint = Color.Unspecified,
            modifier = Modifier
                .size(40.dp)
                .background(Color.Black)
        )
    }

}

@Composable
fun CheckOut(navController: NavHostController, id: Int,  movieList: List<Data>) {
    val movieViewModel = viewModel<MovieViewModel>()
    movieViewModel.id = id
    movieViewModel.getDetailsById()
    val state = movieViewModel.state
    var showDialogScan by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    AddToCartScreen(
        showDialog = showDialog,
        navController = navController,
        id = id,
        movieList = state.movies,
        onDismissRequest = { showDialog = false }
    ) {
        Text("")
    }


    ScanScreen(
        showDialog = showDialogScan,
        navController = navController,
        onDismissRequest = { showDialogScan = false }
    )


    Column(
        modifier = Modifier
            .fillMaxWidth()
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
                onClick = {

                  showDialog = true

                   // navController.navigate("MyCart screen/${movieList[id].id}")
                },
                modifier = Modifier
                    .width(150.dp)
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors( colorResource(R.color.readButton)),
                contentPadding = PaddingValues(15.dp)
            ) {
                Text(text = "Add to Cart", color = Color.White)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ReadyPlayPreview() {
    ReadyPlayTheme {
       // MovieDetailsScreen(id = 1)
    }
}