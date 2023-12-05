package com.cbdn.reports.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cbdn.reports.ui.viewmodel.AppViewModel
import com.cbdn.reports.ui.views.AppMenu
import com.cbdn.reports.ui.views.finishreport.FinishReport
import com.cbdn.reports.ui.views.newreport.NewReport
import com.cbdn.reports.ui.views.searchreports.SearchReports

@Composable
fun AppNavHost(
    navController: NavHostController,
    appViewModel: AppViewModel,
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
            appViewModel.setPrevDestination(Destinations.NewReport.name)
            NewReport(
                appViewModel = appViewModel,
                navController = navController,
            )
        }
        composable(route = Destinations.FinishReport.name) {
            appViewModel.setPrevDestination(Destinations.FinishReport.name)
            FinishReport(
                appViewModel = appViewModel,
                navController = navController,
            )
        }
        composable(route = Destinations.SearchReports.name) {
            appViewModel.setPrevDestination(Destinations.SearchReports.name)
            SearchReports(
                appViewModel = appViewModel,
                navController = navController,
            )
        }
//        composable(route = Destinations.ViewStatistics.name) {
//            appViewModel.setPrevDestination(Destinations.ViewStatistics.name)
//            ViewStatistics()
//        }
    }
}
