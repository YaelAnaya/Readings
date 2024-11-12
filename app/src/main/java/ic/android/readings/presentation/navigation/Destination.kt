package ic.android.readings.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object Readings

@Serializable
object Favorites

@Serializable
object AddReading

@Serializable
data class ReadingDetail(val bookId: Int)


