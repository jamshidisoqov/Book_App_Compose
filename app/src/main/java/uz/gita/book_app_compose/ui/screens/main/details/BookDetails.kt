package uz.gita.book_app_compose.ui.screens.main.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.book_app_compose.data.remote.response.BookData
import uz.gita.book_app_compose.ui.screens.main.details.components.BookDetailsInfo
import uz.gita.book_app_compose.ui.theme.Primary
import uz.gita.book_app_compose.utils.CustomProgressBar
import uz.gita.book_app_compose.utils.CustomTopBarWithNavigate
import uz.gita.book_app_compose.utils.HORIZONTAL_MARGIN_STD

// Created by Jamshid Isoqov on 12/7/2022
class BookDetails(private val bookData: BookData) : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: BookDetailsViewModel = getViewModel<BookDetailsViewModelImpl>()
        LaunchedEffect(key1 = Unit) {
            viewModel.setBookData(bookData = bookData)
        }
        BookDetailsContent(uiState = viewModel.collectAsState().value, onEventDispatcher = viewModel::onEventDispatcher)

    }
}

@Composable
fun BookDetailsContent(uiState: BookDetailUiState, onEventDispatcher: (BookDetailsIntent) -> Unit) {

    val navigator = LocalNavigator.currentOrThrow
    if (uiState.isLoading) {
        CustomProgressBar(progress = true, modifier = Modifier.fillMaxSize())
    }
    val book:BookData? = uiState.bookData

    if (book != null) {
        Column(modifier = Modifier.fillMaxSize()) {
            CustomTopBarWithNavigate(title = book.title, modifier = Modifier.fillMaxWidth()) {
                navigator.pop()
            }
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Author : ${book.author}",
                modifier = Modifier.padding(HORIZONTAL_MARGIN_STD),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier
                    .padding(horizontal = HORIZONTAL_MARGIN_STD)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = HORIZONTAL_MARGIN_STD, vertical = 6.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    BookDetailsInfo(
                        modifier = Modifier
                            .weight(1f)
                            .padding(6.dp),
                        headerTitle = book.pageCount.toString(),
                        subTitle = "Pages"
                    )

                    Spacer(
                        modifier = Modifier
                            .requiredHeight(100.dp)
                            .align(Alignment.CenterVertically)
                            .width(2.dp)
                            .background(color = Primary, shape = RoundedCornerShape(1.dp))
                    )

                    BookDetailsInfo(
                        modifier = Modifier
                            .weight(1f)
                            .padding(6.dp),
                        headerTitle = book.likeCount.toString(),
                        subTitle = "Likes"
                    )

                    Spacer(
                        modifier = Modifier
                            .requiredHeight(100.dp)
                            .align(Alignment.CenterVertically)
                            .width(2.dp)
                            .background(color = Primary, shape = RoundedCornerShape(1.dp))
                    )

                    BookDetailsInfo(
                        modifier = Modifier
                            .weight(1f)
                            .padding(6.dp),
                        headerTitle = book.disLike.toString(),
                        subTitle = "Dislike"
                    )

                }
            }

            Text(
                text = "Description",
                modifier = Modifier
                    .padding(top = HORIZONTAL_MARGIN_STD, start = HORIZONTAL_MARGIN_STD),
                fontSize = 16.sp
            )

            Card(
                modifier = Modifier
                    .padding(HORIZONTAL_MARGIN_STD)
                    .fillMaxWidth()
            ) {

                Text(
                    text = book.description,
                    modifier = Modifier.padding(HORIZONTAL_MARGIN_STD)
                )
            }

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BookDetailsPreview() {
    BookDetailsContent(BookDetailUiState()) {}
}