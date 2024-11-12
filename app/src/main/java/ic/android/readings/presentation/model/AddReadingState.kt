package ic.android.readings.presentation.model

import ic.android.readings.data.database.entity.Book

/**
 * This class is used to represent the state of the UI for adding a book.
 * */
data class AddReadingState(
    val title: String = "",
    val author: String = "",
    val totalPages: Int = 0,
    val imageUrl: String = ""
)


/**
 * Extension function to convert a BookUIState to a Book object.
 * This function is used to convert the UI state to a domain object.
 * */
fun AddReadingState.toBook(): Book {
    return Book(
        title = title,
        author = author,
        totalPages = totalPages,
        imageUrl = imageUrl
    )
}