import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.readyplay.R

@Composable
fun CustomDialog2(openDialogCustom: MutableState<Boolean>) {
    Dialog(onDismissRequest = { openDialogCustom.value = false}) {
        DeleteItemDialog(openDialogCustom = openDialogCustom)
    }
}

@Composable
fun DeleteItemDialog(modifier: Modifier = Modifier, openDialogCustom: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 16.dp)
           // .shadow(elevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 49.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.delete_),
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .padding(top = 50.dp)
            )
            Text(
                text = "Delete Item",
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 20.dp)
            )
            Text(
                text = "Are you sure you want to delete this item?",
                color = Color.Black.copy(alpha = 0.7f),
                fontSize = 12.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 26.dp, top = 14.dp, end = 26.dp)
            )
            Button(
                onClick = { /* TODO: Confirm action */ },
                colors = ButtonDefaults.buttonColors( Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 24.dp, end = 24.dp),
                contentPadding = PaddingValues(15.dp)
            ) {
                Text(
                    text = "Confirm",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
            Text(
                text = "Cancel",
                color = Color.Red,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 20.dp)
            )
        }
    }
}


