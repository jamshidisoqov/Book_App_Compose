@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package uz.gita.book_app_compose.utils

import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import uz.gita.book_app_compose.R
import uz.gita.book_app_compose.ui.theme.Primary

// Created by Jamshid Isoqov on 12/5/2022

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomEditText(
    hint: String,
    text: String,
    modifier: Modifier,
    keyboardOption: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange::invoke,
        modifier = modifier,
        label = { Text(text = hint) },
        keyboardOptions = keyboardOption,
        shape = RoundedCornerShape(ROUNDED_CORNER)
    )
}

@Preview
@Composable
fun CustomEditTextPreview() {
    var phone by remember {
        mutableStateOf("")
    }
    CustomEditText(
        hint = "Enter phone number",
        text = phone,
        modifier = Modifier
            .padding(horizontal = HORIZONTAL_MARGIN_STD, vertical = VERTICAL_MARGIN_STD)
            .fillMaxWidth()
    ) { value ->
        phone = value
    }
}

@Composable
fun CustomAppBottomButton(
    text: String,
    modifier: Modifier,
    isEnabled: Boolean,
    click: () -> Unit
) {
    Button(
        onClick = click::invoke,
        modifier = modifier,
        enabled = isEnabled,
        shape = RoundedCornerShape(size = ROUNDED_CORNER)
    ) {
        Text(text = text, color = if (isEnabled) Color.White else Color.Black)
    }
}

@Preview
@Composable
fun CustomAppBottomButtonPreview() {
    CustomAppBottomButton(
        text = "Ok",
        modifier = Modifier
            .padding(horizontal = HORIZONTAL_MARGIN_STD, vertical = VERTICAL_MARGIN_STD)
            .fillMaxWidth(),
        isEnabled = false
    ) {

    }
}

@Composable
fun CustomProgressBar(
    progress: Boolean,
    modifier: Modifier
) {
    if (progress) {
        Box(modifier = modifier) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview
@Composable
fun CustomProgressBarPreview() {
    val progress by remember {
        mutableStateOf(true)
    }
    CustomProgressBar(
        progress = progress, modifier = Modifier
            .padding(horizontal = HORIZONTAL_MARGIN_STD, vertical = VERTICAL_MARGIN_STD)
            .fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchView(
    hint: String,
    text: String,
    modifier: Modifier,
    onValueChange: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(HORIZONTAL_MARGIN_STD),
        shadowElevation = 4.dp
    ) {

        TextField(
            value = text,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            placeholder = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search"
                    )
                    Text(
                        text = hint,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            },
            shape = RoundedCornerShape(ROUNDED_CORNER),
            onValueChange = onValueChange::invoke,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent
            )
        )
    }
}

@Preview
@Composable
fun CustomSearchViewPreview() {

    CustomSearchView(
        hint = "Search for users",
        text = "",
        modifier = Modifier
            .padding(
                horizontal = HORIZONTAL_MARGIN_STD,
                vertical = VERTICAL_MARGIN_STD
            )
            .fillMaxWidth()
    ) {

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomTopBarWithNavigate(
    title: String,
    modifier: Modifier,
    navigate: () -> Unit
) {
    var selected by remember { mutableStateOf(false) }
    Surface(shadowElevation = 4.dp, modifier = modifier) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = if (selected) Color.DarkGray else Color.White,
                            shape = RoundedCornerShape(percent = 50)
                        )
                        .pointerInteropFilter {
                            selected = it.action == MotionEvent.ACTION_DOWN
                            true
                        }
                        .size(40.dp)
                        .clickable(onClick = navigate::invoke),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_left),
                        contentDescription = "navigate"
                    )
                }
            }
            Text(
                text = title,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CustomTopBar(
    title: String,
    modifier: Modifier
) {
    Surface(shadowElevation = 4.dp, modifier = modifier) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = title,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun CustomTopBarPreview() {
    CustomTopBar(
        title = "Users",
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White)
    )
}

@Composable
fun MultiStyleText(
    text1: String,
    color1: Color,
    text2: String,
    color2: Color,
    modifier: Modifier,
    click: () -> Unit
) {
    Text(buildAnnotatedString {
        withStyle(style = SpanStyle(color = color1)) {
            append(text1)
        }
        withStyle(style = SpanStyle()) {
            append(" ")
        }
        withStyle(
            style = SpanStyle(
                color = color2,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        ) {
            append(text2)
        }
    }, modifier = modifier.clickable(onClick = click::invoke))
}

@Preview
@Composable
fun MultiStyleTextPreview() {
    MultiStyleText(
        text1 = "Not registered yet?",
        color1 = Color.DarkGray,
        text2 = "Register",
        color2 = Primary,
        modifier = Modifier
    ) {

    }
}


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CustomSmsCodeView(
    smsCodeLength: Int,
    textFieldColors: TextFieldColors,
    textStyle: TextStyle,
    smsFulled: (String) -> Unit
) {
    val focusRequesters: List<FocusRequester> = remember {
        (0 until smsCodeLength).map { FocusRequester() }
    }
    val enteredNumbers = remember {
        mutableStateListOf(
            *((0 until smsCodeLength).map { "" }.toTypedArray())
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        for (index in 0 until smsCodeLength) {
            OutlinedTextField(
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp)
                    .focusRequester(focusRequester = focusRequesters[index])
                    .onKeyEvent { keyEvent: KeyEvent ->
                        val currentValue = enteredNumbers.getOrNull(index) ?: ""
                        if (keyEvent.key == Key.Backspace) {
                            if (currentValue.isNotEmpty()) {
                                enteredNumbers[index] = ""
                                smsFulled.invoke(enteredNumbers.joinToString(separator = ""))
                            } else {
                                focusRequesters
                                    .getOrNull(index.minus(1))
                                    ?.requestFocus()
                            }
                        }
                        false
                    },

                textStyle = textStyle,
                singleLine = true,
                value = enteredNumbers.getOrNull(index)?.trim() ?: "",
                maxLines = 1,
                colors = textFieldColors,
                onValueChange = { value: String ->
                    when {
                        value.isDigitsOnly() -> {
                            if (focusRequesters[index].freeFocus()) {
                                when (value.length) {
                                    1 -> {
                                        enteredNumbers[index] = value.trim()
                                        smsFulled.invoke(enteredNumbers.joinToString(separator = ""))
                                        focusRequesters.getOrNull(index + 1)?.requestFocus()
                                    }
                                    2 -> {
                                        focusRequesters.getOrNull(index + 1)?.requestFocus()
                                    }
                                    else -> {
                                        return@OutlinedTextField
                                    }
                                }
                            }
                        }

                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                ), shape = RoundedCornerShape(ROUNDED_CORNER)
            )
            val fulled = enteredNumbers.joinToString(separator = "")
            if (fulled.length == smsCodeLength) {
                smsFulled.invoke(fulled)
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}
