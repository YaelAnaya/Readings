package ic.android.readings.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ic.android.readings.data.database.dao.BookDao
import ic.android.readings.data.database.entity.Book


/**
 * TODO: Modify this class to be the entry point for the database.
 * 1. Annotate the class with @Database and provide the entities and version.
 * 2. Make the class abstract and extend RoomDatabase.
 * */


class ReadingsDatabase {
    // TODO: Add the necessary abstract functions to get the DAOs for the entities.

    companion object {
        private const val DATABASE_NAME = "readings_database"

        @Volatile
        private var instance: ReadingsDatabase? = null

        /**
         * A function to get the instance of the AppDatabase.
         * This function follows the singleton pattern to ensure that only one instance of the database is created.
         * */
        // Uncomment the following code when you have implemented the database.
//        fun getInstance(context: Context): ReadingsDatabase {
//            return instance ?: synchronized(this) {
//                Room.databaseBuilder(
//                    context = context,
//                    klass = ReadingsDatabase::class.java,
//                    name = DATABASE_NAME
//                )   .createFromAsset("database/readings.db")
//                    .build().also { instance = it }
//            }
//        }
    }
}