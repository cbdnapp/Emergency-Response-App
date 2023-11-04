package com.example.cbdnreports.ui.views


import AppNavHost
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cbdnreports.ui.navigation.Destinations


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    windowSize: WindowWidthSizeClass,
    navController: NavHostController = rememberNavController()
) {
    // view model
    // val viewModel: ReportsViewModel = viewModel()

    // navigation
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Destinations.valueOf(
        backStackEntry?.destination?.route ?: Destinations.AppMenu.name
    )

    // adaptive window size
    when (windowSize) {
        WindowWidthSizeClass.Compact -> {}
        WindowWidthSizeClass.Medium -> {}
        WindowWidthSizeClass.Expanded -> {}
        else -> {}
    }

    Scaffold(
        topBar = { ReportsTopBar(
            currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.navigateUp() }
        ) }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
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
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(id = currentScreen.title),
                fontSize = 35.sp
            )
                },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}