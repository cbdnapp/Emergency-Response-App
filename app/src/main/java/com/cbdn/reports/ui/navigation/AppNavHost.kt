import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cbdn.reports.ui.navigation.Destinations
import com.cbdn.reports.ui.viewmodel.SubmitNewViewModel
import com.cbdn.reports.ui.views.AppMenu
import com.cbdn.reports.ui.views.SubmitAmend
import com.cbdn.reports.ui.views.SubmitFinish
import com.cbdn.reports.ui.views.SubmitNew
import com.cbdn.reports.ui.views.ViewFilter
import com.cbdn.reports.ui.views.ViewStatistics

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.AppMenu.name,
        modifier = modifier
    ) {
        composable(route = Destinations.AppMenu.toString()) {
            AppMenu(navController = navController)
        }
        composable(route = Destinations.SubmitNew.name) {
            SubmitNew(viewModel = SubmitNewViewModel())
        }
        composable(route = Destinations.SubmitFinish.name) {
            SubmitFinish()
        }
        composable(route = Destinations.SubmitAmend.name) {
            SubmitAmend()
        }
        composable(route = Destinations.ViewFilter.name) {
            ViewFilter()
        }
        composable(route = Destinations.ViewStatistics.name) {
            ViewStatistics()
        }
    }
}
