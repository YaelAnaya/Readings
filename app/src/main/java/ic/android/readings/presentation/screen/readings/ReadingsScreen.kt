package ic.android.readings.presentation.screen.readings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ic.android.readings.R
import ic.android.readings.data.database.entity.Book
import ic.android.readings.presentation.model.ReadingState
import ic.android.readings.ui.component.AddBookFAB
import ic.android.readings.ui.component.BookDetailBottomSheet
import ic.android.readings.ui.theme.ReadingsTheme
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingsScreen(
    modifier: Modifier = Modifier,
    onAddBook: () -> Unit = {},
    sheetState: SheetState = rememberModalBottomSheetState()
) {
    val vm: ReadingsViewModel = viewModel()
    val state by vm.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            AddBookFAB(onClick = onAddBook)
        },
        floatingActionButtonPosition = FabPosition.End,
    ){ innerPadding ->
        ReadingsScreenContent(
            modifier = modifier.padding(innerPadding),
            state = state,
            sheetState = sheetState,
            onBookDetail = { bookId -> vm.showBookDetail(bookId) },
            onDeleteBook = { book -> vm.deleteBook(book) },
            onDismiss = {
                coroutineScope.launch {
                    vm.hideBookDetail()
                    sheetState.hide()
                }
            }
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReadingsScreenContent(
    modifier: Modifier = Modifier,
    state: ReadingState,
    sheetState: SheetState = rememberModalBottomSheetState(),
    onBookDetail: (Int) -> Unit = {},
    onDismiss: () -> Unit = {},
    onDeleteBook: (Book) -> Unit = {}
) {

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        ReadingsList(
            modifier = modifier,
            readings = state.readings,
            onBookDetail = onBookDetail,
            onDeleteBook = onDeleteBook
        )
        if(state.selectedBook != null) {
            BookDetailBottomSheet(
                modifier = modifier,
                book = state.selectedBook,
                onDismiss = onDismiss,
                sheetState = sheetState
            )
        }
    }

}



@Composable
private fun ReadingsList(
    modifier: Modifier = Modifier,
    readings: List<Book>,
    onBookDetail: (Int) -> Unit,
    onDeleteBook: (Book) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(10.dp)
    ) {
        item {
            HomeScreenHeader()
        }
        items(
            items = readings,
            key = { book -> book.id }
        ) { book ->
            BookItem(
                book = book,
                onBookDetail = { onBookDetail(book.id) },
                onDeleteBook = { onDeleteBook(book) }
            )
            if(readings.indexOf(book) != readings.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                )
            }
        }
    }
}

@Composable
private fun HomeScreenHeader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        DateDisplayRow()
        Text(
            modifier = modifier.padding(16.dp),
            text = "My Readings",
            style = MaterialTheme.typography.headlineSmall
        )
    }

}

@Composable
private fun BookItem(
    modifier: Modifier = Modifier,
    book: Book,
    onBookDetail: () -> Unit,
    onDeleteBook: () -> Unit
) {
    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onBookDetail() },
        headlineContent = {
            Text(
                text = book.title,
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = 18.sp,
            )
        },
        supportingContent = {
            Text(
                modifier = modifier.width(200.dp),
                text = book.author,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall
            )
        },
        trailingContent = {
           IconButton(onClick = { onDeleteBook() }) {
               Icon(
                     painter = painterResource(R.drawable.delete_02),
                     contentDescription = "Delete",
                     tint = MaterialTheme.colorScheme.error.copy(alpha = 0.8f)
               )
           }
        },
        leadingContent = {
            Box(
                modifier = modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painter = painterResource(R.drawable.book_bookmark_02),
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
        },
        colors = ListItemDefaults.colors(
            containerColor = Color.Transparent
        )
    )
}

@SuppressLint("NewApi")
@Composable
private fun DateDisplayRow() {
    // Date format example: "20", "Thu", "Jan 2022"
    val day = LocalDateTime.now().dayOfMonth.toString()
    val dayOfWeek = LocalDateTime.now().dayOfWeek.name.substring(0, 3)
    val monthYear = LocalDateTime.now().month.name.substring(0, 3) + " " + LocalDateTime.now().year



    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp) // Adjust padding as needed
    ) {
        Text(
            modifier = Modifier.fillMaxHeight(),
            text = day,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = dayOfWeek,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = monthYear,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}



private val testState = ReadingState(
    readings = listOf(
        Book(
            id = 1,
            title = "The Great Gatsby",
            author = "F. Scott Fitzgerald",
            totalPages = 180,
            imageUrl = "https://m.media-amazon.com/images/I/81K1jbUMmFL._SL1500_.jpg"
        ),
        Book(
            id = 2,
            title = "Lord of the Flies",
            author = "William Golding",
            totalPages = 224,
            imageUrl = "https://m.media-amazon.com/images/I/716MU3GOvJL._SL1200_.jpg"
        ),
        Book(
            id = 3,
            title = "1984",
            author = "George Orwell",
            totalPages = 328,
            imageUrl = "https://m.media-amazon.com/images/I/71rpa1-kyvL._SL1500_.jpg"
        ),
        Book(
            id = 4,
            title = "Murder on the Orient Express",
            author = "Agatha Christie",
            totalPages = 336,
            imageUrl = "https://m.media-amazon.com/images/I/61rRmiz9HvL._SL1360_.jpg"
        )
    ),
    totalBooks = 4
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Readings Screen")
@Composable
fun ReadingsScreenPreview() {
    ReadingsTheme {
        ReadingsScreenContent(state = testState)
    }
}