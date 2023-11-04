import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cbdnreports.ui.views.AppMenu
import com.example.cbdnreports.ui.views.SubmitReportAmend
import com.example.cbdnreports.ui.views.SubmitReportFinish
import com.example.cbdnreports.ui.views.SubmitReportNew
import com.example.cbdnreports.ui.views.ViewReportFilter
import com.example.cbdnreports.ui.views.ViewReportStatistics
import com.example.cbdnreports.ui.navigation.Destinations

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
            SubmitReportNew()
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