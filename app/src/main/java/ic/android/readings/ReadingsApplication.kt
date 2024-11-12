package ic.android.readings

import android.app.Application
import ic.android.readings.data.database.ReadingsDatabase

/**
 * This class is the entry point of the application.
 * */
class ReadingsApplication: Application() {

    companion object {
        // It is used to provide the database instance for the application.
        lateinit var db: ReadingsDatabase
    }

    // TODO: Override the onCreate function and initialize the database instance.
}