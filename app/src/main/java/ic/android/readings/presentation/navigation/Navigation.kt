package ic.android.readings.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ic.android.readings.presentation.screen.add_book.AddBookScreen
import ic.android.readings.presentation.screen.readings.ReadingsScreen

/**
 * Composable function that defines the navigation for the readings app.
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingsNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Readings,
    ) {
        // Define the routes for the app using the composable<T> function.
        // Use the Destination classes in the [Destination.kt] file.
        composable<Readings> {
            ReadingsScreen(
                onAddBook = { navController.navigate(AddReading) },
            )
        }
        composable<AddReading> {
            AddBookScreen(
                onBack = { navController.popBackStack() },
            )
        }
    }
}