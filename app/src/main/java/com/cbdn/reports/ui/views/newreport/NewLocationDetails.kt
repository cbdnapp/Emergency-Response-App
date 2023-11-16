package com.cbdn.reports.ui.views.newreport

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cbdn.reports.R
import com.cbdn.reports.ui.viewmodel.NewReportViewModel
import com.cbdn.reports.ui.views.composables.BasicTextField
import com.cbdn.reports.ui.views.composables.FormDivider
import com.cbdn.reports.ui.views.composables.FormHeader
import com.cbdn.reports.ui.views.composables.FormSubHeader

@Composable
fun NewLocationDetails(
    viewModel: NewReportViewModel
) {
    val reportState by viewModel.reportState.collectAsStateWithLifecycle()
//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FormHeader(textResource = R.string.submit_location_header)

        // LOCATION
        FormSubHeader(textResource = R.string.location)
        BasicTextField(
            value = reportState.location,
            updateValue = { viewModel.setLocation(it) },
            labelResource = R.string.enter_location
        )
        FormDivider()
    }
}