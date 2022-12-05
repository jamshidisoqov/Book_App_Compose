package uz.gita.book_app_compose.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import uz.gita.book_app_compose.data.remote.response.BookData
import uz.gita.book_app_compose.utils.CustomSearchView
import uz.gita.book_app_compose.utils.HORIZONTAL_MARGIN_STD
import uz.gita.book_app_compose.utils.ROUNDED_CORNER

// Created by Jamshid Isoqov on 12/5/2022
class MainScreen : AndroidScreen() {
    @Composable
    override fun Content() {

    }
}

@Composable
fun MainScreenContent() {

    var search by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.background(color = Color.LightGray).fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {

            CustomSearchView(
                hint = "Search for books",
                text = search,
                modifier = Modifier.fillMaxWidth()
            ) {
                search = it
            }
            Spacer(modifier = Modifier.height(HORIZONTAL_MARGIN_STD))

            LazyColumn (modifier = Modifier.weight(1f)){
                items(10) {
                    MyBookItem(modifier = Modifier
                        .padding(4.dp)
                        .clickable {

                        }
                        .fillMaxWidth()
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(size = ROUNDED_CORNER)
                        ),
                        bookData = BookData(
                            id = 0,
                            title = "Counting star",
                            author = "One republic",
                            description = "",
                            pageCount = 123
                        ),
                        onFavourite = {},
                        onEdit = { },
                        onDelete = {}
                    )
                }
            }


        }

    }

}
@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview(){
    MainScreenContent()
}