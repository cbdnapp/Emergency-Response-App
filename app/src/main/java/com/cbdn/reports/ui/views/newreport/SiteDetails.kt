package com.cbdn.reports.ui.views.newreport

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cbdn.reports.R
import com.cbdn.reports.data.VictimCodeData
import com.cbdn.reports.ui.viewmodel.AppViewModel
import com.cbdn.reports.ui.views.composables.FormAddVictimDialog
import com.cbdn.reports.ui.views.composables.FormButton
import com.cbdn.reports.ui.views.composables.FormDateTimeSelection
import com.cbdn.reports.ui.views.composables.FormDivider
import com.cbdn.reports.ui.views.composables.FormHeader
import com.cbdn.reports.ui.views.composables.FormMultiLineTextField
import com.cbdn.reports.ui.views.composables.FormSubHeader
import com.cbdn.reports.ui.views.composables.FormSubHeaderWithArgs
import com.cbdn.reports.ui.views.composables.FormSwitchWithTextField
import com.cbdn.reports.ui.views.composables.FormVictimInfoCard

@Composable
fun SiteDetails(
    viewModel: AppViewModel,
    modifier: Modifier
) {
    val reportState by viewModel.reportState.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FormHeader(textResource = R.string.submit_on_scene_details_header)

        // VICTIM INFO
        FormSubHeader(
            textResource = (R.string.victim_info)
        )
        Row(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.full_field_width)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Row(
                Modifier.padding(
                    start=dimensionResource(id = R.dimen.thin_spacing),
                    end=dimensionResource(id = R.dimen.thin_spacing),
                    )
            ) {
                FormSubHeaderWithArgs(
                    textResource = R.string.victim_count_with_count,
                    formatArgs = reportState.victimInfo.size)
                FormButton(
                    onClick = { viewModel.toggleVictimDialog() },
                    labelResource = R.string.add,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        if (uiState.addVictimDialog) {
            FormAddVictimDialog(
                onConfirmation = { viewModel.addVictim() },
                onDismiss = { viewModel.cancelVictimDialog() },
                onEdit = { viewModel.updateVictim() },
                optionsVictimCodes = VictimCodeData.getCode(),
                statusCode = uiState.victimStatusCode,
                name = uiState.victimName,
                age = uiState.victimAge,
                identification = uiState.victimIdentification,
                setStatusCode = { viewModel.setVictimStatusCode(it) },
                setName = { viewModel.setVictimName(it) },
                setAge = { viewModel.setVictimAge(it) },
                setIdentification = { viewModel.setVictimIdentification(it) },
                victimEditIndex = uiState.victimEditIndex
            )
        }
        reportState.victimInfo.forEachIndexed { index, item ->
            FormVictimInfoCard(
                index = index,
                statusCode = item.statusCode,
                name = item.name,
                age = item.age,
                identification = item.identification,
                remove = { viewModel.removeVictim(index) },
                edit = { viewModel.initiateVictimEdit(index)}
            )
            Spacer(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.thin_spacing))
            )
        }
        FormDivider()

        // NOTES
        FormSubHeader(textResource = R.string.notes)
        FormMultiLineTextField(
            value = reportState.notes,
            updateValue = { viewModel.setNotes(it) },
            labelResource =  R.string.enter_notes,
        )
        FormDivider()

        // DATE AND TIME OF ARRIVAL
        FormSubHeader(textResource = R.string.date_and_time_of_arrival)
        FormDateTimeSelection(
            displayValue = reportState.datetimeArrival,
            updateDatetime = { viewModel.setDatetimeArrival(it)}
        )
        FormDivider()

        // POLICE PRESENT
        FormSwitchWithTextField(
            checked = uiState.policeCheck,
            onChange = { viewModel.setPoliceCheck(it) },
            labelResource = R.string.police_present,
            value = reportState.policePresent,
            updateValue = { viewModel.setPolicePresent(it) }
        )
        FormDivider()

        // AMBULANCE PRESENT
        FormSwitchWithTextField(
            checked = uiState.ambulanceCheck,
            onChange = { viewModel.setAmbulanceCheck(it) },
            labelResource = R.string.ambulance_present,
            value = reportState.ambulancePresent,
            updateValue = { viewModel.setAmbulancePresent(it) }
        )
        FormDivider()

        // ELECTRIC COMPANY PRESENT
        FormSwitchWithTextField(
            checked = uiState.electricCompanyCheck,
            onChange = { viewModel.setElectricCompanyCheck(it) },
            labelResource = R.string.electric_company_present,
            value = reportState.electricCompanyPresent,
            updateValue = { viewModel.setElectricCompanyPresent(it) }
        )
        FormDivider()

        // TRANSIT POLICE PRESENT
        FormSwitchWithTextField(
            checked = uiState.transitPoliceCheck,
            onChange = { viewModel.setTransitPoliceCheck(it) },
            labelResource = R.string.transit_police_present,
            value = reportState.transitPolicePresent,
            updateValue = { viewModel.setTransitPolicePresent(it) }
        )
        FormDivider()


    }
}