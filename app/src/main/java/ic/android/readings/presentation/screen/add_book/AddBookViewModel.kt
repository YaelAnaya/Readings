package ic.android.readings.presentation.screen.add_book

import androidx.lifecycle.ViewModel
import ic.android.readings.data.database.dao.BookDao
import ic.android.readings.data.database.entity.Book
import ic.android.readings.presentation.model.AddReadingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddBookViewModel :ViewModel() {
    private val bookDao: BookDao? = null

    private val _state: MutableStateFlow<AddReadingState> = MutableStateFlow(AddReadingState())
    val state = _state.asStateFlow()

    /**
     * TODO: Implement addBook function.
     * This function should add a book to the database.
     * It uses the bookDao to insert the book.
     * */
    fun addBook(book: Book) {

    }

    fun onValueChange(addReadingState: AddReadingState) {
        _state.update { addReadingState }
    }
}