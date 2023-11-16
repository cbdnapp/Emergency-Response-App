package com.cbdn.reports.ui.views.submit.start

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
import com.cbdn.reports.ui.navigation.SubmitNewNavHost
import com.cbdn.reports.ui.viewmodel.SubmitNewViewModel
import com.cbdn.reports.ui.views.composables.OnPrimaryTextButton
import com.cbdn.reports.ui.views.composables.OnSecondaryText

@Composable
fun SubmitNew(
    navController: NavHostController = rememberNavController(),
    viewModel: SubmitNewViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            SubmitNewTopBar(
                navController = navController,
                updateCurrentScreen = { viewModel.setCurrentScreen(it) },
                dispatchComplete = uiState.dispatchComplete,
                locationComplete = uiState.locationComplete,
                onSceneComplete = uiState.onSceneComplete,
                submitComplete = uiState.submitComplete
            )
        },
        bottomBar = {
            SubmitNewBottomBar(
                navController = navController,
                viewModel = viewModel,
                currentScreen = uiState.currentScreen,
                submitReady = uiState.reportComplete,
                updateCurrentScreen = { viewModel.setCurrentScreen(it) }
            )
        }
    ) {innerPadding ->
        SubmitNewNavHost(
            navController = navController,
            viewModel = viewModel,
            modifier = Modifier
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
                        navController.navigate(Destinations.SubmitNewDispatch.name)
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
                        navController.navigate(Destinations.SubmitNewLocation.name)
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
                        navController.navigate(Destinations.SubmitNewOnScene.name)
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
                        navController.navigate(Destinations.SubmitNewSubmit.name)
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
    viewModel: SubmitNewViewModel,
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
                    enabled = currentScreen != "SubmitNewDispatch",
                    onClick = {
                        when(currentScreen) {
                            "SubmitNewLocation" ->
                                navController.navigate(Destinations.SubmitNewDispatch.name)
                            "SubmitNewOnScene" ->
                                navController.navigate(Destinations.SubmitNewLocation.name)
                            "SubmitNewSubmit" ->
                                navController.navigate(Destinations.SubmitNewOnScene.name)
                        }
                        updateCurrentScreen(navController.currentBackStackEntry?.destination?.route)
                    },
                    labelResource = R.string.previous_button,
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
                    enabled = currentScreen != "SubmitNewSubmit",
                    onClick = {
                        when(currentScreen) {
                            "SubmitNewDispatch" ->
                                navController.navigate(Destinations.SubmitNewLocation.name)
                            "SubmitNewLocation" ->
                                navController.navigate(Destinations.SubmitNewOnScene.name)
                            "SubmitNewOnScene" ->
                                navController.navigate(Destinations.SubmitNewSubmit.name)
                        }
                        updateCurrentScreen(navController.currentBackStackEntry?.destination?.route)
                    },
                    labelResource = R.string.next_button,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(dimensionResource(id = R.dimen.thin_spacing))
                )
            }
        },
    )
}