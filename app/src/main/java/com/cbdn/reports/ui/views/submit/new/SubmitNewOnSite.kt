package com.cbdn.reports.ui.views.submit.new

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cbdn.reports.R
import com.cbdn.reports.ui.viewmodel.SubmitNewViewModel
import com.cbdn.reports.ui.views.composables.DateTimeSelection
import com.cbdn.reports.ui.views.composables.FormHeader
import com.cbdn.reports.ui.views.composables.FormSubHeader
import com.cbdn.reports.ui.views.composables.SwitchWithTextField

@Composable
fun SubmitNewOnSite(
    viewModel: SubmitNewViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        FormHeader(textResource = R.string.submit_on_scene_details_header)

        // DATE AND TIME OF ARRIVAL
        FormSubHeader(textResource = R.string.date_and_time_of_arrival)
        DateTimeSelection(
            displayValue = uiState.datetimeArrival,
            updateDatetime = { viewModel.setDatetimeArrival(it)}
        )

        // POLICE PRESENT
        SwitchWithTextField(
            checked = uiState.policeCheck,
            onChange = { viewModel.setPoliceCheck(it) },
            labelResource = R.string.police_present,
            value = uiState.policePresent,
            updateValue = { viewModel.setPolicePresent(it) }
        )

        // AMBULANCE PRESENT
        SwitchWithTextField(
            checked = uiState.ambulanceCheck,
            onChange = { viewModel.setAmbulanceCheck(it) },
            labelResource = R.string.ambulance_present,
            value = uiState.ambulancePresent,
            updateValue = { viewModel.setAmbulancePresent(it) }
        )

        // ELECTRIC COMPANY PRESENT
        SwitchWithTextField(
            checked = uiState.electricCompanyCheck,
            onChange = { viewModel.setElectricCompanyCheck(it) },
            labelResource = R.string.electric_company_present,
            value = uiState.electricCompanyPresent,
            updateValue = { viewModel.setElectricCompanyPresent(it) }
        )

        // TRANSIT POLICE PRESENT
        SwitchWithTextField(
            checked = uiState.transitPoliceCheck,
            onChange = { viewModel.setTransitPoliceCheck(it) },
            labelResource = R.string.transit_police_present,
            value = uiState.transitPolicePresent,
            updateValue = { viewModel.setTransitPolicePresent(it) }
        )

        // VICTIM COUNT

        // VICTIM INFO

        // NOTES
        FormSubHeader(textResource = R.string.notes)
        TextField(
            value = uiState.notes ?: "",
            onValueChange = { viewModel.setNotes(it) },
            isError = uiState.notes == null,
            label = { Text(text = stringResource(id = R.string.notes)) },
            trailingIcon = {
                Text(
                    text = stringResource(id = R.string.required),
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.thin_spacing))
                )
            },
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.full_field_width)),
        )
        
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thick_spacing)))
    }
}