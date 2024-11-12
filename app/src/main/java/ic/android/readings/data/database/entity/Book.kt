package ic.android.readings.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * TODO: Implement the Book entity class.
 * This class should represent the readings table in the database.
 * It should have the following columns:
 * - id: int (Primary Key) (Auto Generate)
 * - title: string
 * - author: string
 * - total_pages: int (Column Name: total_pages)
 * - image_url: string (Column Name: image_url)
 * */

data class Book (
    val id: Int = 0,
    val title: String,
    val author: String,
    val totalPages: Int,
    val imageUrl: String,
)
