package com.cbdn.reports.ui.views.submit.start

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
import com.cbdn.reports.ui.viewmodel.SubmitNewViewModel
import com.cbdn.reports.ui.views.composables.BasicTextField
import com.cbdn.reports.ui.views.composables.DateTimeSelection
import com.cbdn.reports.ui.views.composables.FormDivider
import com.cbdn.reports.ui.views.composables.FormHeader
import com.cbdn.reports.ui.views.composables.FormSubHeader

@Composable
fun SubmitSubmittal(
    viewModel: SubmitNewViewModel
) {
    val reportState by viewModel.reportState.collectAsStateWithLifecycle()
//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FormHeader(textResource = R.string.submit_submittal_details_header)

        // DATE AND TIME OF RETURN
        FormSubHeader(textResource = R.string.date_and_time_of_return)
        DateTimeSelection(
            displayValue = reportState.datetimeReturn,
            updateDatetime = { viewModel.setDatetimeReturn(it)}
        )
        FormDivider()

        // AUTHOR
        FormSubHeader(textResource = R.string.report_writer)
        BasicTextField(
            value = reportState.reportWriter,
            updateValue = { viewModel.setReportWriter(it) },
            labelResource = R.string.enter_name
        )
        FormDivider()
    }
}