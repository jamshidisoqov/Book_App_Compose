package uz.gita.book_app_compose.ui.screens.main.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import uz.gita.book_app_compose.ui.screens.main.details.components.BookDetailsInfo
import uz.gita.book_app_compose.ui.theme.Primary
import uz.gita.book_app_compose.utils.CustomTopBarWithNavigate
import uz.gita.book_app_compose.utils.HORIZONTAL_MARGIN_STD

// Created by Jamshid Isoqov on 12/7/2022
class BookDetails : AndroidScreen() {
    @Composable
    override fun Content() {

    }
}

@Composable
fun BookDetailsContent() {
    Column(modifier = Modifier.fillMaxSize()) {

        CustomTopBarWithNavigate(title = "Leo Messi", modifier = Modifier.fillMaxWidth()) {

        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Author : Leo Messi",
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
                    headerTitle = "123",
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
                    headerTitle = "12",
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
                    headerTitle = "82",
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
                text = LoremIpsum(50).values.joinToString(" "),
                modifier = Modifier.padding(
                    HORIZONTAL_MARGIN_STD
                )
            )

        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BookDetailsPreview() {
    BookDetailsContent()
}