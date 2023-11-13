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
import com.cbdn.reports.ui.views.composables.FormHeader

@Composable
fun SubmitNewLocation(
    viewModel: SubmitNewViewModel
) {
    val reportState by viewModel.reportState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FormHeader(textResource = R.string.submit_location_header)

        // LOCATION
        BasicTextField(
            value = reportState.location,
            updateValue = { viewModel.setLocation(it) },
            labelResource = R.string.location
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thick_spacing)))
    }
}