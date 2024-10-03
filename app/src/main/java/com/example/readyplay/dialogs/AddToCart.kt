import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.readyplay.R
import com.example.readyplay.model.Data
import com.example.readyplay.ui.theme.ReadyPlayTheme


@Composable
fun AddToCartScreen(showDialog: Boolean,
                    onDismissRequest: () -> Unit,
                    navController: NavHostController,
                    id: Int, movieList: List<Data>,
                    content: @Composable () -> Unit
) {
    if (showDialog){
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
            ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color.Black)
                    .padding(horizontal = 16.dp)
                    .shadow(elevation = 4.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(bottom = 49.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.mark_good),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                        .padding(top = 50.dp)
                )


                Text(
                    text = "Added to cart",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 20.dp)
                )


                Text(
                    text = "2 items",
                    color = Color.Black.copy(alpha = 0.7f),
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp,
                    modifier = Modifier
                        .padding(top = 14.dp, start = 26.dp, end = 26.dp)
                        .fillMaxWidth()
                )


                Button(
                    onClick = {
                        navController.navigate("MyCart screen/${movieList[id].id}")
                    },
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.readButton)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, start = 24.dp, end = 24.dp)
                ) {
                    Text(
                        text = "Go to Cart",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }


                Text(
                    text = "Continue shopping",
                    color = colorResource(R.color.readButton),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable {
                            navController.navigate("Home screen")

                        }
                        .padding(top = 20.dp)
                )
            }
        }

    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun ReadyPlayPreview() {
    ReadyPlayTheme {

    }
}
