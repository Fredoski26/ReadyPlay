package com.example.readyplay.home
import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.readyplay.R
import com.example.readyplay.dialogs.ScanScreen
import com.example.readyplay.model.Data
import com.example.readyplay.ui.theme.ReadyPlayTheme
import com.example.readyplay.viewmodel.MovieViewModel
import com.example.readyplay.viewmodel.ScreenState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(navController: NavHostController) {
    val movieViewModel = viewModel<MovieViewModel>()
    val state = movieViewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.black))
            .padding( vertical = 20.dp)
    ) {
        GreetingSection()
        Spacer(modifier = Modifier.height(20.dp))
        SearchSection()
        Spacer(modifier = Modifier.height(20.dp))
        DealsSection(state, movieViewModel,)
        Spacer(modifier = Modifier.height(10.dp))
        RecommendationSection(state, movieViewModel, navController)
        Spacer(modifier = Modifier.height(10.dp))
        BottomNavBar()
    }
}

@Composable
fun GreetingSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp)
            .height(120.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Hello Daniel",
                color = Color.White,
                fontSize = 32.sp
            )

            Image(
                painter = painterResource(id = R.drawable.shopping_cart),
                contentDescription = "Cart",
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.Unspecified),
               // colorFilter = ColorFilter.tint(colorResource(id = R.color.seeAllColor))
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        TabRow(
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
fun TabRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "All",
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Animation",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Slice of Life",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "Adventure",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@SuppressLint("ResourceType")
@Composable
fun SearchSection() {
   val  navController: NavHostController
    Card(
        modifier = Modifier
            .padding(horizontal = 28.dp)
            .fillMaxWidth(),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )

    ){
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()

                // .background(colorResource(id = R.drawable.search_bg))
                .height(50.dp),
            hint = "search",

        )
    }


}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DealsSection(state: ScreenState,
                 movieViewModel: MovieViewModel,) {

    Row(modifier = Modifier.fillMaxWidth()
        .padding(horizontal = 28.dp)
        .verticalScroll(rememberScrollState())
        , Arrangement.Absolute.SpaceBetween) {
        Text(
            text = "Deals of the day",
            color = Color.White,
            fontSize = 14.sp,


        )
        Text(
            text = "See all",
            color = colorResource(id = R.color.seeAllColor),
            fontSize = 14.sp,

        )
    }
    /*Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        AutoSlidingCarousel(
            itemsCount = movieViewModel.state.movies.size,
            itemContent = { index ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movieViewModel.state.popularMovies[index].images)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(200.dp)
                )
            }
        )
    }*/
    SliderSection()
  // SliderItem(id = 1)


}



@Composable
fun SliderSection() {
    var iconVisible by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
    ){
        Box(
            modifier = Modifier
                .height(177.dp)
                .background(Color.White)
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .fillMaxWidth()

        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Start
            ) {

                Image(
                    painter = painterResource(id = R.drawable.product_),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp, 120.dp)
                        .padding(10.dp)
                        .align(Alignment.CenterVertically),
                    contentScale = ContentScale.Fit
                )


                Spacer(modifier = Modifier.width(38.dp))

                Column(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {

                    Text(
                        text = "Drama",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "N6300",
                            color = colorResource(R.color.readButton),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.width(10.dp))


                        Text(
                            text = "N7400",
                            color = Color(0xFF9E9E9E),
                            fontSize = 14.sp,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                    Spacer(modifier = Modifier.height(1.dp))


                    Text(
                        text = "The Shawshank Redemotion",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(1.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Rated",
                            color = Color(0xFF9E9E9E),
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "R",
                            color = Color(0xFF9E9E9E),
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.TopEnd)
                    .padding(top = 10.dp, end = 20.dp)
            ){
                Icon(
                    Icons.Default.Favorite,
                    tint = colorResource(R.color.deleteColor),
                    //painter = painterResource(id = R.drawable.mak_group),
                    contentDescription = null,
                    modifier = Modifier
                        .size(15.dp)
                        .clickable {

                        }
                        .align(alignment = Alignment.TopEnd)

                )
            }
        }
    }

}




@Composable
fun RecommendationSection(
    state: ScreenState,
    movieViewModel: MovieViewModel,
    navController: NavHostController
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 28.dp)
        .height(285.dp)
    ) {
        Text(
            text = "Recommended for you",
            color = Color.White,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(1.dp))
       // MovieList()
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(1.dp),
            content = {
                items(state.movies.size) {
                    if (it >= state.movies.size - 1 && !state.endReached && !state.isLoading) {
                        movieViewModel.loadNextItems()
                    }
                    ProductItem(
                        itemIndex = it, movieList = state.movies,
                        navController = navController
                    )
                }
            }

        )
    }
}



@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    autoSlideDuration: Long = 200,
    pagerState: PagerState = remember { PagerState() },
    itemsCount: Int,
    itemContent: @Composable (index: Int) -> Unit,
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    LaunchedEffect(pagerState.currentPage) {
        delay(autoSlideDuration)
        pagerState.animateScrollToPage((pagerState.currentPage + 1) % itemsCount)
    }

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        HorizontalPager(count = itemsCount, state = pagerState) { page ->
            itemContent(page)

        }

        Surface(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.BottomCenter),
            shape = CircleShape,
            color = Color.Black.copy(alpha = 0.5f)
        ) {
            DotsIndicator(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                totalDots = itemsCount,
                selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage,
                dotSize = 8.dp
            )
        }
    }
}

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = Color.White,
    unSelectedColor: Color = Color.Red,
    dotSize: Dp
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unSelectedColor,
                size = dotSize
            )

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Composable
fun IndicatorDot(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}

/*@Composable
fun MovieList() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(1.dp)

    ) {
        items(10) {
            ProductItem()
        }
    }
}*/
@SuppressLint("ResourceType")
@Composable
fun SliderItem(id: Int) {
    val movieViewModel = viewModel<MovieViewModel>()
    movieViewModel.id = id
    movieViewModel.getDetailsById()
    val state = movieViewModel.state

    val details = state.detailsData

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .width(width = 1.dp)
            .verticalScroll(scrollState)
            .padding(horizontal = 1.dp)
    ) {
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
                        model = details.images,
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
    }

    }







@SuppressLint("ResourceType")
@Composable
fun ProductItem(itemIndex: Int, movieList: List<Data>, navController: NavHostController) {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .height(285.dp)
            .background(color = Color.Black)
            .padding(horizontal = 1.dp, vertical = 8.dp)
    ) {
       Card(
           modifier = Modifier
               .wrapContentWidth()
               .fillMaxWidth()
               .clickable {
                   navController.navigate("MovieDetails screen/${movieList[itemIndex].id}")
               }
               .padding(horizontal = 8.dp),


           colors = CardDefaults.cardColors(
               containerColor = Color.White
           ),
           shape = RoundedCornerShape(24.dp),
           elevation = CardDefaults.cardElevation(
               defaultElevation = 4.dp
           )

       ) {
           Box(
               modifier = Modifier
                   .size(176.dp)
               /* .background(
                    painter = painterResource(id = R.drawable.item_bg),
                    contentScale = ContentScale.FillBounds
                )*/
           ) {
//               Spacer(modifier = Modifier.height(4.dp))
//               Icon(
//                   painter = painterResource(id = R.drawable.ic_favorite_border),
//                   contentDescription = "Favorite",
//                   tint = Color.White,
//                   modifier = Modifier.size(24.dp)
//               )


               AsyncImage(
                   model = movieList[itemIndex].poster,
                   contentDescription = movieList[itemIndex].title,
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

       }

        Text(
            text = "N10,700",
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 18.dp, top = 8.dp)
        )

        Text(
            text = movieList[itemIndex].title,
            color =  colorResource(id = R.color.movieColor),
            fontSize = 14.sp,
            maxLines = 2,
            modifier = Modifier
                .width(160.dp)
                .padding(top = 2.dp, start = 18.dp,)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ResourceType")
@Composable
fun SearchBar(modifier: Modifier = Modifier, hint: String) {
    var showDialog by remember { mutableStateOf(false) }
    val navController = rememberNavController()
    ScanScreen(
        showDialog = showDialog,
       navController = navController,
        onDismissRequest = { showDialog = false }
    )

    Box(
        modifier = modifier
            .background(colorResource(R.color.white))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                text = hint,
                color = Color.Gray,

            )
            Icon(painterResource(R.drawable.scan, ),   tint = null, contentDescription = null, Modifier
                .size(40.dp)
                .width(50.dp)
                .clickable {
            showDialog = true
            })

        }

    }



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavBar() {
    var selectedIndex by remember { mutableStateOf(0) }

    BottomNavigation(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxSize(),
        contentColor = colorResource(R.color.hintColor),
        elevation = 8.dp
    ) {
        BottomNavigationItem(
            unselectedContentColor = colorResource(R.color.seeAllColor),
            selectedContentColor = colorResource(R.color.readButton),
            modifier = Modifier.fillMaxSize(),
            icon = { Icon(painterResource(R.drawable.home),tint = null, contentDescription = "Store") },
            label = { Text("Store" , maxLines = 1, color = colorResource(R.color.hintColor)) },
            selected = selectedIndex == 0,
            onClick = { selectedIndex = 0 }
        )
        BottomNavigationItem(
            selectedContentColor = colorResource(R.color.readButton),
            modifier = Modifier.fillMaxSize(),
            icon = { Icon(painterResource(R.drawable.heart_circle), tint = null, contentDescription = "Saved") },
            label = { Text("Saved" , maxLines = 1, color = colorResource(R.color.hintColor) )},
            selected = selectedIndex == 1,
            unselectedContentColor = colorResource(R.color.hintColor),
            onClick = { selectedIndex = 1 }
        )
        BottomNavigationItem(
            selectedContentColor = colorResource(R.color.readButton),
            modifier = Modifier.fillMaxSize(),
            icon = { Icon(painterResource(R.drawable.send_2),tint = null, contentDescription = "Payments") },
            label = { Text("Payments" , maxLines = 1, color = colorResource(R.color.hintColor)) },
            selected = selectedIndex == 2,
            unselectedContentColor = colorResource(R.color.hintColor),
            onClick = { selectedIndex = 2 }
        )
        BottomNavigationItem(
            selectedContentColor = colorResource(R.color.readButton),
            modifier = Modifier.fillMaxSize(),
            icon = { Icon(painterResource(R.drawable.heart_circle), tint = null, contentDescription = "History") },
            label = { Text("History" , maxLines = 1, color = colorResource(R.color.hintColor))},
            selected = selectedIndex == 3,
            unselectedContentColor = colorResource(R.color.hintColor),
            onClick = { selectedIndex = 3 }
        )
        BottomNavigationItem(
            selectedContentColor = colorResource(R.color.readButton),
            modifier = Modifier.fillMaxSize(),
            icon = { Icon(painterResource(R.drawable.user), tint = null, contentDescription = "Settings") },
            label = { Text("Settings", maxLines = 1, color = colorResource(R.color.hintColor))  },
            selected = selectedIndex == 4,
            unselectedContentColor = colorResource(R.color.hintColor),
            onClick = { selectedIndex = 4 }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    ReadyPlayTheme {
   HomeScreen(navController)
    }
}
