package com.cbdn.reports.ui.views.newreport

import OnSecondaryIcon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cbdn.reports.R
import com.cbdn.reports.ui.navigation.Destinations
import com.cbdn.reports.ui.navigation.NewReportNavHost
import com.cbdn.reports.ui.viewmodel.NewReportViewModel
import com.cbdn.reports.ui.views.composables.OnPrimaryTextButton
import com.cbdn.reports.ui.views.composables.OnSecondaryText

@Composable
fun NewReport(
    navController: NavHostController = rememberNavController(),
    newReportViewModel: NewReportViewModel = NewReportViewModel()
) {
    val uiState by newReportViewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            SubmitNewTopBar(
                navController = navController,
                updateCurrentScreen = { newReportViewModel.setCurrentScreen(it) },
                dispatchComplete = uiState.dispatchDetailsComplete,
                locationComplete = uiState.locationDetailsComplete,
                onSceneComplete = uiState.siteDetailsComplete,
                submitComplete = uiState.submittalDetailsComplete
            )
        },
        bottomBar = {
            SubmitNewBottomBar(
                navController = navController,
                viewModel = newReportViewModel,
                currentScreen = uiState.currentScreen,
                submitReady = uiState.reportComplete,
                updateCurrentScreen = { newReportViewModel.setCurrentScreen(it) }
            )
        }
    ) {innerPadding ->
        NewReportNavHost(
            navController = navController,
            viewModel = newReportViewModel,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubmitNewTopBar(
    navController: NavHostController,
    updateCurrentScreen: (String?) -> Unit,
    dispatchComplete: Boolean,
    locationComplete: Boolean,
    onSceneComplete: Boolean,
    submitComplete: Boolean
) {
    CenterAlignedTopAppBar(
        title = {},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
            ),
        actions = {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ){
                TextButton(
                    onClick = {
                        navController.navigate(Destinations.NewDispatchDetails.name)
                        updateCurrentScreen(
                            navController.currentBackStackEntry?.destination?.route)
                    }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (dispatchComplete) {
                            OnSecondaryIcon(iconResource = R.drawable.baseline_check_box_24)
                        } else {
                            OnSecondaryIcon(iconResource = R.drawable.baseline_check_box_outline_blank_24)
                        }
                        OnSecondaryText(textResource = R.string.dispatch)
                    }
                }
                TextButton(
                    onClick = {
                        navController.navigate(Destinations.NewLocationDetails.name)
                        updateCurrentScreen(
                            navController.currentBackStackEntry?.destination?.route)
                    }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (locationComplete) {
                            OnSecondaryIcon(iconResource = R.drawable.baseline_check_box_24)
                        } else {
                            OnSecondaryIcon(iconResource = R.drawable.baseline_check_box_outline_blank_24)
                        }
                        OnSecondaryText(textResource = R.string.location)
                    }
                }
                TextButton(
                    onClick = {
                        navController.navigate(Destinations.NewSiteDetails.name)
                        updateCurrentScreen(
                            navController.currentBackStackEntry?.destination?.route)
                    }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (onSceneComplete) {
                            OnSecondaryIcon(iconResource = R.drawable.baseline_check_box_24)
                        } else {
                            OnSecondaryIcon(iconResource = R.drawable.baseline_check_box_outline_blank_24)
                        }
                        OnSecondaryText(textResource = R.string.on_scene)
                    }
                }
                TextButton(
                    onClick = {
                        navController.navigate(Destinations.NewSubmittalDetails.name)
                        updateCurrentScreen(
                            navController.currentBackStackEntry?.destination?.route)
                    }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (submitComplete) {
                            OnSecondaryIcon(iconResource = R.drawable.baseline_check_box_24)
                        } else {
                            OnSecondaryIcon(iconResource = R.drawable.baseline_check_box_outline_blank_24)
                        }
                        OnSecondaryText(textResource = R.string.submit)
                    }
                }
            }
        }
    )
}

@Composable
fun SubmitNewBottomBar(
    navController: NavHostController,
    viewModel: NewReportViewModel,
    currentScreen: String?,
    submitReady: Boolean,
    updateCurrentScreen: (String?) -> Unit
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        actions = {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ){
                OnPrimaryTextButton(
                    enabled = currentScreen != Destinations.NewDispatchDetails.name,
                    onClick = {
                        when(currentScreen) {
                            Destinations.NewLocationDetails.name ->
                                navController.navigate(Destinations.NewDispatchDetails.name)
                            Destinations.NewSiteDetails.name ->
                                navController.navigate(Destinations.NewLocationDetails.name)
                            Destinations.NewSubmittalDetails.name ->
                                navController.navigate(Destinations.NewSiteDetails.name)
                        }
                        updateCurrentScreen(navController.currentBackStackEntry?.destination?.route)
                    },
                    labelResource = R.string.previous,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(dimensionResource(id = R.dimen.thin_spacing))
                )
                OnPrimaryTextButton(
                    enabled = submitReady,
                    onClick = { viewModel.submitReport() },
                    labelResource = R.string.submit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(dimensionResource(id = R.dimen.thin_spacing))
                )
                OnPrimaryTextButton(
                    enabled = currentScreen != Destinations.NewSubmittalDetails.name,
                    onClick = {
                        when(currentScreen) {
                            Destinations.NewDispatchDetails.name ->
                                navController.navigate(Destinations.NewLocationDetails.name)
                            Destinations.NewLocationDetails.name ->
                                navController.navigate(Destinations.NewSiteDetails.name)
                            Destinations.NewSiteDetails.name ->
                                navController.navigate(Destinations.NewSubmittalDetails.name)
                        }
                        updateCurrentScreen(navController.currentBackStackEntry?.destination?.route)
                    },
                    labelResource = R.string.next,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(dimensionResource(id = R.dimen.thin_spacing))
                )
            }
        },
    )
}