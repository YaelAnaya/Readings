package ic.android.readings.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ic.android.readings.presentation.navigation.ReadingsNavigation

@Composable
fun ReadingsApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    ReadingsNavigation(
        modifier = modifier.fillMaxSize(),
        navController = navController
    )
}