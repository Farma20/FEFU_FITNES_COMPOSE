import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.AllBookingUI
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.MainMenuUI
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Navigation.Screen
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Navigation.TimeTableScreen
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.UI.TimeTableUI
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TimeTableNav() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = TimeTableScreen.TimeTableView.route){
        composable(
            route = TimeTableScreen.TimeTableView.route,
            exitTransition = {
                slideOutHorizontally (
                    targetOffsetX = {-1080},
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            popEnterTransition = {
                slideInHorizontally (
                    initialOffsetX = {-1080},
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            },
        ){
            TimeTableUI(navController = navController)

        }
        composable(
            route = TimeTableScreen.ServicesView.route,
            enterTransition = {
                slideInHorizontally (
                    initialOffsetX = {1080},
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            popExitTransition = {
                slideOutHorizontally (
                    targetOffsetX = {1080},
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            },
        ){
            AllBookingUI()
        }
    }
}