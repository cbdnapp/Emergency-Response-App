package com.cbdn.reports.ui.views.finishreport

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cbdn.reports.ui.viewmodel.AppViewModel
import com.cbdn.reports.ui.views.composables.LazyColumnOfReports
import com.cbdn.reports.ui.views.composables.SingleReport

@Composable
fun FinishReport(
    appViewModel: AppViewModel,
) {
    val uiState by appViewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.pulledReports == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
            LaunchedEffect(Unit) {
                appViewModel.getUnfinishedReports()
            }
        }
    } else if (uiState.pulledReports!!.isNotEmpty()) {

        if (uiState.reportItemIndex != null) {
            SingleReport(
                reportData = uiState.pulledReports!![uiState.reportItemIndex!!],
                optionDelete = { /*TODO*/ },
                optionFinish = { /*TODO*/ },
                modifier = Modifier
            )
        } else {
            LazyColumnOfReports(
                items= uiState.pulledReports!!,
                selectReport = { appViewModel.setReportItemIndex(it) },
                modifier = Modifier.fillMaxSize()
            )
        }
    } else {
        Text(text = "No Results")
    }
}

