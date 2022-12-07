@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package uz.gita.book_app_compose.ui.screens.main.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import uz.gita.book_app_compose.data.remote.response.UserData
import uz.gita.book_app_compose.utils.CustomTopBarWithNavigate
import uz.gita.book_app_compose.utils.HORIZONTAL_MARGIN_STD

// Created by Jamshid Isoqov on 12/6/2022
class UsersScreen : AndroidScreen() {
    @Composable
    override fun Content() {

    }
}

@Composable
fun UsersScreenContent() {
    Column(modifier = Modifier.fillMaxSize()) {

        CustomTopBarWithNavigate(title = "Users", modifier = Modifier.fillMaxWidth()) {

        }
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(10) {
                UserItem(
                    userData = UserData(0, "Jamshid", "Isoqov"), modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = HORIZONTAL_MARGIN_STD,
                            vertical = 6.dp
                        )
                ) {


                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun UsersScreenPreview() {
    UsersScreenContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserItem(userData: UserData, modifier: Modifier, onClick: () -> Unit) {
    Card(onClick = onClick::invoke, modifier = modifier) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(HORIZONTAL_MARGIN_STD)) {
            Text(
                text = "${userData.firstName} ${userData.lastName}",
                modifier = Modifier.padding(bottom = HORIZONTAL_MARGIN_STD),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "+99 890 714 41 02"
            )
        }
    }
}

@Preview
@Composable
fun UserItemPreview() {
    UserItem(
        userData = UserData(0, "Jamshid", "Isoqov"), modifier = Modifier
            .fillMaxWidth()
            .padding(HORIZONTAL_MARGIN_STD)
    ) {

    }
}