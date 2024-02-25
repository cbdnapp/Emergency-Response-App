package com.cbdn.reports.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cbdn.reports.R
import com.cbdn.reports.ui.navigation.AppNavHost
import com.cbdn.reports.ui.navigation.Destinations
import com.cbdn.reports.ui.viewmodel.AppViewModel
import com.cbdn.reports.ui.views.composables.FormButton
import com.cbdn.reports.ui.views.composables.DialogHeader


@Composable
fun App(
    navController: NavHostController = rememberNavController(),
    appViewModel: AppViewModel
) {
    val uiState by appViewModel.uiState.collectAsStateWithLifecycle()
    val reportState by appViewModel.reportState.collectAsStateWithLifecycle()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Destinations.valueOf(
        backStackEntry?.destination?.route ?: Destinations.AppMenu.name
    )
    Scaffold(
        topBar = {
            ReportsTopBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    if (currentScreen.name == Destinations.AppMenu.name){
                        navController.popBackStack()
                    } else {
                        navController.popBackStack(
                            route = Destinations.AppMenu.name,
                            inclusive = false
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        if (currentScreen == Destinations.AppMenu) {
            when (uiState.backButtonPrev) {
                Destinations.NewReport.name -> {
                    if (!uiState.submitSuccessful) {
                        if (reportState.finalized) {
                            Dialog(onDismissRequest = {
                                appViewModel.resetUI()
                            }) {
                                Column(
                                    modifier = Modifier
                                        .background(color = MaterialTheme.colorScheme.background)
                                        .padding(dimensionResource(id = R.dimen.moderate_spacing)),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Icon(Icons.Rounded.Warning, null)
                                    DialogHeader(R.string.not_submitted)
                                    Text(stringResource(id = R.string.not_submitted_message))
                                    Row {
                                        FormButton(
                                            onClick = {
                                                appViewModel.resetUI()
                                            },
                                            labelResource = R.string.discard
                                        )
                                        FormButton(
                                            onClick = {
                                                appViewModel.submitReport()
                                                appViewModel.resetUI()
                                            },
                                            labelResource = R.string.submit
                                        )
                                    }
                                }
                            }
                        } else {
                            Dialog(onDismissRequest = {
                                appViewModel.resetUI()
                            }) {
                                Column(
                                    modifier = Modifier
                                        .background(color = MaterialTheme.colorScheme.background)
                                        .padding(dimensionResource(id = R.dimen.moderate_spacing)),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Icon(Icons.Rounded.Warning, null)
                                    DialogHeader(R.string.not_submitted)
                                    Text(stringResource(id = R.string.incomplete_message))
                                    Row {
                                        FormButton(
                                            onClick = {
                                                appViewModel.resetUI()
                                            },
                                            labelResource = R.string.discard
                                        )
                                        FormButton(
                                            onClick = {
                                                appViewModel.submitReport()
                                                appViewModel.resetUI()
                                            },
                                            labelResource = R.string.save_as_incomplete
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        Dialog(onDismissRequest = {
                            appViewModel.resetUI()
                        }) {
                            Column(
                                modifier = Modifier
                                    .background(color = MaterialTheme.colorScheme.background)
                                    .padding(dimensionResource(id = R.dimen.moderate_spacing)),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Icon(Icons.Rounded.Done, null)
                                DialogHeader(R.string.submitted_successfully)
                                Text(stringResource(id = R.string.submit_successful_message))
                                FormButton(
                                    onClick = {
                                        appViewModel.resetUI()
                                    },
                                    labelResource = R.string.ok
                                )
                            }
                        }
                    }
                }
//                else -> { appViewModel.resetUI() }
            }
        }
        AppNavHost(
            navController = navController,
            appViewModel = appViewModel,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsTopBar(
    currentScreen: Destinations,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(id = currentScreen.title),
                color = MaterialTheme.colorScheme.onPrimary
            )
                },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    onClick = navigateUp,
                    colors = IconButtonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        contentColor = MaterialTheme.colorScheme.primary,
                        disabledContainerColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = null,
                    )
                }
            }
        }
    )
}