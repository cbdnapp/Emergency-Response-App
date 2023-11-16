import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cbdn.reports.ui.navigation.Destinations
import com.cbdn.reports.ui.viewmodel.NewReportViewModel
import com.cbdn.reports.ui.views.AppMenu
import com.cbdn.reports.ui.views.finishreport.FinishReport
import com.cbdn.reports.ui.views.newreport.NewReport
import com.cbdn.reports.ui.views.searchreports.SearchReports
import com.cbdn.reports.ui.views.viewstatistics.ViewStatistics

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
        composable(route = Destinations.NewReport.name) {
            NewReport(viewModel = NewReportViewModel())
        }
        composable(route = Destinations.FinishReport.name) {
            FinishReport()
        }
        composable(route = Destinations.SearchReports.name) {
            SearchReports()
        }
        composable(route = Destinations.ViewStatistics.name) {
            ViewStatistics()
        }
    }
}
