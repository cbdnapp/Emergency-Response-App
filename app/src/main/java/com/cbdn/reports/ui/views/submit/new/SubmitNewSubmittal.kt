package com.cbdn.reports.ui.views.submit.new

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cbdn.reports.R
import com.cbdn.reports.ui.viewmodel.SubmitNewViewModel
import com.cbdn.reports.ui.views.composables.BasicTextField
import com.cbdn.reports.ui.views.composables.DateTimeSelection
import com.cbdn.reports.ui.views.composables.FormHeader
import com.cbdn.reports.ui.views.composables.FormSubHeader

@Composable
fun SubmitSubmittal(
    viewModel: SubmitNewViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        FormHeader(textResource = R.string.submit_submittal_details_header)

        // DATE AND TIME OF RETURN
        FormSubHeader(textResource = R.string.date_and_time_of_return)
        DateTimeSelection(
            displayValue = uiState.datetimeReturn,
            updateDatetime = { viewModel.setDatetimeReturn(it)}
        )

        // AUTHOR
        BasicTextField(
            value = uiState.author,
            updateValue = { viewModel.setAuthor(it) },
            labelResource = R.string.author
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thick_spacing)))
    }
}