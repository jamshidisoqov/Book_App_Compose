@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package uz.gita.book_app_compose.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.book_app_compose.R
import uz.gita.book_app_compose.data.remote.response.BookData
import uz.gita.book_app_compose.ui.theme.Primary
import uz.gita.book_app_compose.ui.theme.Primary_Trans
import uz.gita.book_app_compose.utils.HORIZONTAL_MARGIN_STD
import uz.gita.book_app_compose.utils.ROUNDED_CORNER
import uz.gita.book_app_compose.utils.VERTICAL_MARGIN_STD

// Created by Jamshid Isoqov on 12/6/2022


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyBookItem(
    modifier: Modifier,
    bookData: BookData,
    onFavourite: (Boolean) -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier
                .padding(start = HORIZONTAL_MARGIN_STD)
                .requiredHeight(120.dp)
                .padding(start = HORIZONTAL_MARGIN_STD),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                text = bookData.title,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = VERTICAL_MARGIN_STD)
            )
            Text(
                text = bookData.author,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = VERTICAL_MARGIN_STD)
            )
            Text(
                text = "Page count: ${bookData.pageCount}"
            )

        }
        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier
                .padding(HORIZONTAL_MARGIN_STD),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {


            Icon(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .size(40.dp)
                    .background(
                        color = Primary_Trans,
                        shape = RoundedCornerShape(size = 4.dp)
                    )
                    .padding(8.dp)
                    .pointerInteropFilter {

                        true
                    },
                painter = painterResource(id = R.drawable.ic_heart),
                tint = Primary,
                contentDescription = "Favourite"
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_edit),
                tint = Color.Yellow,
                contentDescription = "Edit",
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .size(40.dp)
                    .background(
                        color = Primary_Trans,
                        shape = RoundedCornerShape(size = 4.dp)
                    )
                    .padding(8.dp)
                    .pointerInteropFilter {

                        true
                    }
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_delete),
                tint = Color.Red,
                contentDescription = "Delete",
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Primary_Trans,
                        shape = RoundedCornerShape(size = 4.dp)
                    )
                    .padding(8.dp)
                    .pointerInteropFilter {

                        true
                    }
            )

        }

    }

}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MyBookItemUsers(
    modifier: Modifier,
    bookData: BookData,
    onClick: () -> Unit,
    onFavourite: (Boolean) -> Unit
) {

    Card(onClick = onClick::invoke, modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .padding(start = HORIZONTAL_MARGIN_STD)
                    .requiredHeight(90.dp)
                    .padding(start = HORIZONTAL_MARGIN_STD),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    text = bookData.title,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Text(
                    text = bookData.author,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Text(
                    text = "Page count: ${bookData.pageCount}"
                )

            }
            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .padding(HORIZONTAL_MARGIN_STD),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Icon(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .size(40.dp)
                        .background(
                            color = Primary_Trans,
                            shape = RoundedCornerShape(size = 4.dp)
                        )
                        .padding(8.dp)
                        .pointerInteropFilter {

                            true
                        },
                    painter = painterResource(id = R.drawable.ic_heart),
                    tint = Primary,
                    contentDescription = "Favourite"
                )
            }
        }

    }
}


@Preview
@Composable
fun MyBookItemUsersPreview() {
    MyBookItemUsers(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
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
        onClick = {

        }
    ) {

    }
}


@Preview
@Composable
fun MyBookItemPreview() {
    MyBookItem(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
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
        onFavourite = {

        },
        onEdit = {

        },
        onDelete = {

        }
    )
}
