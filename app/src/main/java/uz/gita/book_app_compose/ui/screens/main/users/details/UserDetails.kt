package uz.gita.book_app_compose.ui.screens.main.users.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import uz.gita.book_app_compose.data.remote.response.BookData
import uz.gita.book_app_compose.data.remote.response.UserData
import uz.gita.book_app_compose.ui.screens.main.MyBookItemUsers
import uz.gita.book_app_compose.utils.CustomTopBarWithNavigate
import uz.gita.book_app_compose.utils.HORIZONTAL_MARGIN_STD

// Created by Jamshid Isoqov on 12/6/2022

class UserDetails(userData: UserData) : AndroidScreen() {
    @Composable
    override fun Content() {

    }
}

@Composable
fun UserDetailsContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopBarWithNavigate(title = "Jamshid Isoqov", modifier = Modifier.fillMaxWidth()) {

        }
        LazyColumn(modifier = Modifier.weight(1f).padding(top = 6.dp)) {
            items(10) {
                MyBookItemUsers(
                    modifier = Modifier
                        .padding(horizontal = HORIZONTAL_MARGIN_STD, vertical = 3.dp)
                        .fillMaxWidth()
                        .padding(6.dp),
                    bookData = BookData(
                        id = 0,
                        title = "Counting star",
                        author = "One republic",
                        description = "",
                        pageCount = 123
                    ),
                    onClick = {

                    }
                ) {

                }

            }

        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun BookDetailsPreview() {
    UserDetailsContent()
}