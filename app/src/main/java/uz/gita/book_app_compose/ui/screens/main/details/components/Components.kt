package uz.gita.book_app_compose.ui.screens.main.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Created by Jamshid Isoqov on 12/7/2022


@Composable
fun BookDetailsInfo(modifier: Modifier, headerTitle: String, subTitle: String) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = headerTitle, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text(text = subTitle, fontSize = 16.sp)
    }
}