package ic.android.readings.presentation.model

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import ic.android.readings.data.database.entity.Book

/**
 * This class is used to represent the state of the UI for the readings screen.
 * */
data class ReadingState(
    val readings: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val totalBooks: Int = 0,
    val selectedBook: Book? = null
)