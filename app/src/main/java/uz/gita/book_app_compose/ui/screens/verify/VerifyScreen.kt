@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package uz.gita.book_app_compose.ui.screens.verify

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import uz.gita.book_app_compose.ui.theme.Primary
import uz.gita.book_app_compose.utils.*

// Created by Jamshid Isoqov on 12/5/2022

class VerifyScreen : AndroidScreen() {
    @Composable
    override fun Content() {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyScreenContent() {
    Column(modifier = Modifier.fillMaxSize()) {

        var isEnabled by remember { mutableStateOf(false) }

        CustomTopBar(
            title = "Confirm code",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .size(56.dp)
        )

        Spacer(
            modifier = Modifier
                .height(120.dp)
                .align(Alignment.CenterHorizontally)
        )

        CustomSmsCodeView(
            smsCodeLength = 6,
            textFieldColors = TextFieldDefaults.textFieldColors(),
            textStyle = MaterialTheme.typography.titleMedium
        ) {
            isEnabled = it.length == 6
        }

        Spacer(modifier = Modifier.weight(1f))

        MultiStyleText(
            text1 = "Code expire date",
            color1 = Color.DarkGray,
            text2 = "00:39",
            color2 = Primary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {

        }

        CustomAppBottomButton(
            text = "Confirm code",
            modifier = Modifier
                .padding(
                    horizontal = HORIZONTAL_MARGIN_STD,
                    vertical = VERTICAL_MARGIN_STD
                )
                .fillMaxWidth(),
            isEnabled = isEnabled
        ) {

        }


    }
}

@Preview(showSystemUi = true)
@Composable
fun VerifyScreenPreview() {
    VerifyScreenContent()
}