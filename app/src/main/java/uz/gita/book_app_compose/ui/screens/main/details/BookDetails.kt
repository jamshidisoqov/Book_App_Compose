package uz.gita.book_app_compose.ui.screens.main.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.androidx.AndroidScreen
import uz.gita.book_app_compose.utils.CustomTopBarWithNavigate

// Created by Jamshid Isoqov on 12/7/2022
class BookDetails : AndroidScreen() {
    @Composable
    override fun Content() {

    }
}

@Composable
fun BookDetailsContent(){
    Column(modifier = Modifier.fillMaxSize()) {

        CustomTopBarWithNavigate(title = "Leo Messi", modifier = Modifier.fillMaxWidth()) {

        }



    }
}

@Preview
@Composable
fun BookDetailsPreview(){
    BookDetailsContent()
}