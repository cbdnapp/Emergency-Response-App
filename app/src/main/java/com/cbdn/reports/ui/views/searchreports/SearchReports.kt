package com.cbdn.reports.ui.views.searchreports

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.cbdn.reports.R
import com.cbdn.reports.ui.navigation.Destinations
import com.cbdn.reports.ui.viewmodel.AppViewModel
import com.cbdn.reports.ui.views.composables.DateRangeSelection
import com.cbdn.reports.ui.views.composables.ReportViewer
import com.cbdn.reports.ui.views.composables.DialogHeader
import com.cbdn.reports.ui.views.composables.ReportsLazyColumn

@Composable
fun SearchReports(
    navController: NavHostController,
    appViewModel: AppViewModel
) {
    val uiState by appViewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (uiState.reportItemIndex == null) {
            TextField(
                value = uiState.searchString ?: "",
                onValueChange = { appViewModel.setSearch(it) },
                label = { Text(text = stringResource(id = R.string.search)) },
                singleLine = true,
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.full_field_width))
                    .padding(top = dimensionResource(id = R.dimen.thin_spacing)),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null,
                        modifier = Modifier.clickable { appViewModel.getFilteredReports() }
                    )
                },
            )
            DateRangeSelection(
                fromValue = uiState.searchFrom,
                toValue = uiState.searchTo,
                changeFrom = { appViewModel.setSearchFrom(it) },
                changeTo = { appViewModel.setSearchTo(it) }
            )
        }
        if (uiState.pulledReports == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
                LaunchedEffect(Unit) {
                    appViewModel.getFinishedReports()
                }
            }
        } else if (uiState.pulledReports!!.isNotEmpty()) {

            if (uiState.reportItemIndex != null) {
                val reportId= uiState.pulledReports!![uiState.reportItemIndex!!].first
                val report= uiState.pulledReports!![uiState.reportItemIndex!!].second

                ReportViewer(
                    report = report,
                    reportId = reportId,
                    clickPrevious = { appViewModel.clearReportItemIndex() },
                    showDelete = true,
                    clickDelete = {
                        appViewModel.deleteReport(reportId)
                        appViewModel.clearPulledReports()
                        appViewModel.clearReportItemIndex()
                    },
                    showAmend = true,
                    clickAmend = {
                        appViewModel.importReport(report, reportId)
                        Log.d("DEV", "UI: Current Dest: ${navController.currentDestination?.route.toString()}")
                        appViewModel.setReportModification(navController.currentDestination?.route.toString())
                        navController.navigate(Destinations.NewReport.name)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                ReportsLazyColumn(
                    items= uiState.pulledReports!!,
                    selectReport = { appViewModel.setReportItemIndex(it) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DialogHeader(textResource = R.string.no_results)
            }
        }
    }
}