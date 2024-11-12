package ic.android.readings.presentation.screen.add_book

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ic.android.readings.data.database.entity.Book
import ic.android.readings.presentation.model.AddReadingState
import ic.android.readings.presentation.model.toBook
import ic.android.readings.ui.component.TopAppBar

@Composable
fun AddBookScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {}
) {
    val vm: AddBookViewModel = viewModel()
    val state by vm.state.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = "New reading",
                onBack = { onBack() }
            )
        },
    ){ innerPadding ->
        AddBookContent(
            modifier = Modifier.padding(innerPadding),
            state = state,
            onValueChange = { vm.onValueChange(it) },
            onAddBook = { book ->
                vm.addBook(book)
                onBack()
            }
        )
    }
}

@Composable
private fun AddBookContent(
    modifier: Modifier = Modifier,
    state: AddReadingState,
    onValueChange: (AddReadingState) -> Unit,
    onAddBook: (Book) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AddBookTextField(
            value = state.title,
            onValueChange = { onValueChange(state.copy(title = it)) },
            label = "Title"
        )
        AddBookTextField(
            value = state.author,
            onValueChange = { onValueChange(state.copy(author = it)) },
            label = "Author"
        )
        AddBookTextField(
            value = state.imageUrl,
            onValueChange = { onValueChange(state.copy(imageUrl = it)) },
            label = "Image URL"
        )
        AddBookTextField(
            value = if (state.totalPages == 0) "" else state.totalPages.toString(),
            onValueChange = { onValueChange(state.copy(totalPages = if(it.isNotEmpty()) it.toInt() else 0)) },
            label = "Pages",
            keyboardType = KeyboardType.Number
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = modifier
                .fillMaxWidth(),
            onClick = { onAddBook(state.toBook()) }
        ) {
            Text("Add Book")
        }
    }
}


@Composable
private fun AddBookTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = FontFamily.Serif
                )
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        keyboardActions = KeyboardActions(onDone = { focusRequester.freeFocus() }),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        )
    )
}