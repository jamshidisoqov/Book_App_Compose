package uz.gita.book_app_compose.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import uz.gita.book_app_compose.utils.CustomAppBottomButton
import uz.gita.book_app_compose.utils.HORIZONTAL_MARGIN_STD

// Created by Jamshid Isoqov on 12/8/2022


@Composable
fun ErrorDialog(error: String,onDismiss:()->Unit) {
    var showDialog by remember { mutableStateOf(true) }
    if (showDialog) {
        Dialog(onDismissRequest = { }) {
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(200.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {

                    Text(
                        text = "Error",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )

                    Spacer(modifier = Modifier.height(HORIZONTAL_MARGIN_STD))

                    Text(text = error, fontSize = 16.sp)

                    Spacer(modifier = Modifier.weight(1f))

                    CustomAppBottomButton(
                        text = "Close",
                        modifier = Modifier.fillMaxWidth(),
                        isEnabled = true
                    ) {
                        showDialog = false
                        onDismiss.invoke()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorDialogPreview() {
    ErrorDialog(error = "No internet connection"){}
}

@Preview(showBackground = true)
@Composable
fun MessageDialogPreview() {
    MessageDialog(message = "No internet connection"){}
}

@Composable
fun MessageDialog(message: String,onDismiss:()->Unit) {
    var showDialog by remember { mutableStateOf(true) }
    if (showDialog) {
        Dialog(onDismissRequest = { }) {
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(200.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {

                    Text(
                        text = "Message",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(HORIZONTAL_MARGIN_STD))

                    Text(text = message, fontSize = 16.sp)

                    Spacer(modifier = Modifier.weight(1f))

                    CustomAppBottomButton(
                        text = "Close",
                        modifier = Modifier.fillMaxWidth(),
                        isEnabled = true
                    ) {
                        showDialog = false
                        onDismiss.invoke()
                    }
                }
            }
        }
    }
}

@Composable
fun DeleteDialog(
    message: String = "Are you really delete book?",
    onDelete: (Boolean) -> Unit
) {
    var showDialog by remember { mutableStateOf(true) }
    if (showDialog) {
        Dialog(onDismissRequest = { }) {
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(200.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {

                    Text(
                        text = "Delete",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )

                    Spacer(modifier = Modifier.height(HORIZONTAL_MARGIN_STD))

                    Text(text = message, fontSize = 16.sp)

                    Spacer(modifier = Modifier.weight(1f))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        TextButton(onClick = {
                            showDialog = false
                            onDelete.invoke(true)
                        }, modifier = Modifier.weight(1f)) {
                            Text(text = "Delete")
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        CustomAppBottomButton(
                            text = "Cancel",
                            modifier = Modifier.weight(1f),
                            isEnabled = true
                        ) {
                            onDelete.invoke(false)
                            showDialog = false
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteDialogPreview() {
    DeleteDialog("Are you really delete book?") {

    }
}