package ic.android.readings.presentation.screen.readings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ic.android.readings.ReadingsApplication
import ic.android.readings.data.database.dao.BookDao
import ic.android.readings.data.database.entity.Book
import ic.android.readings.presentation.model.ReadingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ReadingsViewModel: ViewModel() {
    private val bookDao: BookDao? = null

    private val _state: MutableStateFlow<ReadingState> = MutableStateFlow(ReadingState())
    val state: StateFlow<ReadingState> = _state.asStateFlow()

    init {
        getBooks()
    }

    /**
     * This function should get all the books from the database and update the uiState
     * with the readings and totalBooks.
     * It uses the bookDao to get all the books.
     * */
    private fun getBooks() {

    }

    /**
     * TODO: Implement deleteBook function.
     * This function should delete a book from the database.
     * It uses the bookDao to delete the book.
     * */
    fun deleteBook(book: Book) {

    }

    /**
     * TODO: Implement the event to show the book detail bottom sheet.
     * This function should update the uiState to show the book detail bottom sheet.
     * */
    fun showBookDetail(bookId: Int) {

    }

    /**
     * TODO: Implement the event to hide the book detail bottom sheet.
     * This function should update the uiState to hide the book detail bottom sheet.
     */
    fun hideBookDetail() {
        _state.update { it.copy(selectedBook = null) }
    }

}