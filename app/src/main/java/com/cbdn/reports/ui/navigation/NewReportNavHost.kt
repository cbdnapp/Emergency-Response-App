package com.cbdn.reports.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cbdn.reports.ui.viewmodel.NewReportViewModel
import com.cbdn.reports.ui.views.newreport.NewDispatchDetails
import com.cbdn.reports.ui.views.newreport.NewLocationDetails
import com.cbdn.reports.ui.views.newreport.NewSiteDetails
import com.cbdn.reports.ui.views.newreport.NewSubmittalDetails


@Composable
fun NewReportNavHost(
    navController: NavHostController,
    viewModel: NewReportViewModel,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.NewDispatchDetails.name,
        modifier = modifier
    ) {
        composable(route = Destinations.NewDispatchDetails.name) {
            NewDispatchDetails(viewModel = viewModel)
        }
        composable(route = Destinations.NewLocationDetails.name) {
            NewLocationDetails(viewModel = viewModel)
        }
        composable(route = Destinations.NewSiteDetails.name) {
            NewSiteDetails(viewModel = viewModel)
        }
        composable(route = Destinations.NewSubmittalDetails.name) {
            NewSubmittalDetails(viewModel = viewModel)
        }
    }
}