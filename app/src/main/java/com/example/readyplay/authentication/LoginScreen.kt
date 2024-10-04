package com.example.readyplay.authentication

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.readyplay.R
import com.example.readyplay.navigator.Navigation
import com.example.readyplay.ui.theme.ReadyPlayTheme

@SuppressLint("UnrememberedMutableState")
@Composable
fun LoginScreen(navController: NavHostController) {

    val scrollState = rememberScrollState()

    var email by remember { mutableStateOf("") }
    val emailPass = "danielsedidiong@gmail.com"
    val passwordPass = "daniel123"
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var showErrorPass by remember { mutableStateOf(false) }




    val openDialogFeedback: MutableState<Boolean> = mutableStateOf(false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(top = 10.dp, bottom = 40.dp)
    ) {
        Text(
            text = "Log in",
            color = Color(0xFF3F51B5),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 20.dp, top = 32.dp)
        )

        Text(
            text = "Login now to start making seamless and efficient self-checkouts.",
            color = Color(0xFF757575),
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 20.dp, top = 15.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Email Address",
            color = Color(0xFF9E9E9E),
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 20.dp)
        )

        OutlinedTextField(
            value = email,
            textStyle = TextStyle(
                color = Color.Black
            ),
            onValueChange = { email = it
                showError = false },
            label = { Text("name@example.com") },
            isError = showError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 1.dp),
            singleLine = true
        )

        if (showError) {
            Text(
                text = "This email address does not have an account",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                .padding(start = 20.dp, top = 12.dp),
                textAlign = TextAlign.Start
            )
        }

        Text(
            text = "Password",
            color = Color(0xFF9E9E9E),
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 20.dp, top = 12.dp)
        )

        OutlinedTextField(
            value = password,
            textStyle = TextStyle(
                color = Color.Black
            ),
            onValueChange = { password = it
                showErrorPass = false },
            label = { Text("Password") },
            isError = showErrorPass,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    painterResource(R.drawable.icon_pass)
                else painterResource(R.drawable.icon_pass)

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(image, contentDescription = null)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 1.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        if (showErrorPass){
            Text(
                text = "Incorrect password",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 12.dp),
                textAlign = TextAlign.Start
            )

        }


        TextButton(
            onClick = {

            },
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 24.dp, top = 8.dp)
        ) {
            Text(
                text = "Forgot Password?",
                color = Color(0xFF3F51B5),
                fontSize = 14.sp
            )
        }

        Button(
            onClick = {

                if (email != emailPass) {
                    showError = true
                }else if (password != passwordPass){
                  showErrorPass = true
                }else{

                    navController.navigate("Home screen")
                }

               // navController.navigate("Home screen")

            },
            colors = ButtonDefaults.buttonColors( colorResource(R.color.readButton)),
            //colors = ButtonDefaults.buttonColors(Color(0xFF3F51B5)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .height(50.dp)
        ) {
            Text(
                text = "Log in",
                color = Color.White,
                fontSize = 16.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            TextButton(onClick = {  }) {
                Text(
                    text = "Don’t have an account?",
                    color = Color(0xFF9E9E9E),
                    fontSize = 14.sp
                )
            }


            TextButton(onClick = {  }) {
                Text(
                    text = "Sign up",
                    color = Color(0xFF3F51B5),
                    fontSize = 14.sp
                )
            }
        }
    }
    @Composable
    fun FeedbackDialog(context: Context) {
        val openDialog by openDialogFeedback
        if (openDialog) {
            Dialog(onDismissRequest = {openDialogFeedback.value = false}) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(color = Color.Black)
                        .padding(horizontal = 16.dp)
                        .shadow(elevation = 4.dp)
                        .background(color = Color.White)
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
                        onClick = {  },
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
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }

            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun ReadyPlayPreview() {
    val navController = rememberNavController()
    ReadyPlayTheme {

        LoginScreen(navController = navController)
    }
}


