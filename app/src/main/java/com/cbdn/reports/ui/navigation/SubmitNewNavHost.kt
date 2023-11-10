package com.cbdn.reports.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cbdn.reports.ui.viewmodel.SubmitNewViewModel
import com.cbdn.reports.ui.views.submit.new.SubmitNewDispatch
import com.cbdn.reports.ui.views.submit.new.SubmitNewLocation
import com.cbdn.reports.ui.views.submit.new.SubmitNewOnSite
import com.cbdn.reports.ui.views.submit.new.SubmitSubmittal


@Composable
fun SubmitNewNavHost(
    navController: NavHostController,
    viewModel: SubmitNewViewModel,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.SubmitNewDispatch.name,
        modifier = modifier
    ) {
        composable(route = Destinations.SubmitNewDispatch.name) {
            SubmitNewDispatch(viewModel = viewModel)
        }
        composable(route = Destinations.SubmitNewLocation.name) {
            SubmitNewLocation(viewModel = viewModel)
        }
        composable(route = Destinations.SubmitNewOnScene.name) {
            SubmitNewOnSite(viewModel = viewModel)
        }
        composable(route = Destinations.SubmitNewSubmit.name) {
            SubmitSubmittal(viewModel = viewModel)
        }
    }
}