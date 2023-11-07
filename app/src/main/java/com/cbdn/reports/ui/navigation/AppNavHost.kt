import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cbdn.reports.ui.views.AppMenu
import com.cbdn.reports.ui.views.SubmitReportAmend
import com.cbdn.reports.ui.views.SubmitReportFinish
import com.cbdn.reports.ui.views.SubmitReportNew
import com.cbdn.reports.ui.views.ViewReportFilter
import com.cbdn.reports.ui.views.ViewReportStatistics
import com.cbdn.reports.ui.navigation.Destinations
import com.cbdn.reports.ui.viewmodel.SubmitReportNewViewModel

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
        composable(route = Destinations.SubmitReportNew.name) {
            SubmitReportNew(viewModel = SubmitReportNewViewModel())
        }
        composable(route = Destinations.SubmitReportFinish.name) {
            SubmitReportFinish()
        }
        composable(route = Destinations.SubmitReportAmend.name) {
            SubmitReportAmend()
        }
        composable(route = Destinations.ViewReportFilter.name) {
            ViewReportFilter()
        }
        composable(route = Destinations.ViewReportStatistics.name) {
            ViewReportStatistics()
        }
    }
}