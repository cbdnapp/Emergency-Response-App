package com.cbdn.reports.ui.views.submit.new

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
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
import com.cbdn.reports.data.VictimCodeData
import com.cbdn.reports.data.VictimCodes
import com.cbdn.reports.ui.viewmodel.SubmitNewViewModel
import com.cbdn.reports.ui.views.composables.BasicTextField
import com.cbdn.reports.ui.views.composables.DateTimeSelection
import com.cbdn.reports.ui.views.composables.DropDownTextField
import com.cbdn.reports.ui.views.composables.FormButton
import com.cbdn.reports.ui.views.composables.FormHeader
import com.cbdn.reports.ui.views.composables.FormSubHeader
import com.cbdn.reports.ui.views.composables.FormSubHeaderWithArgs
import com.cbdn.reports.ui.views.composables.SwitchWithTextField

@Composable
fun SubmitNewOnSite(
    viewModel: SubmitNewViewModel
) {
    val reportState by viewModel.reportState.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FormHeader(textResource = R.string.submit_on_scene_details_header)

        // DATE AND TIME OF ARRIVAL
        FormSubHeader(textResource = R.string.date_and_time_of_arrival)
        DateTimeSelection(
            displayValue = reportState.datetimeArrival,
            updateDatetime = { viewModel.setDatetimeArrival(it)}
        )

        // POLICE PRESENT
        SwitchWithTextField(
            checked = uiState.policeCheck,
            onChange = { viewModel.setPoliceCheck(it) },
            labelResource = R.string.police_present,
            value = reportState.policePresent,
            updateValue = { viewModel.setPolicePresent(it) }
        )

        // AMBULANCE PRESENT
        SwitchWithTextField(
            checked = uiState.ambulanceCheck,
            onChange = { viewModel.setAmbulanceCheck(it) },
            labelResource = R.string.ambulance_present,
            value = reportState.ambulancePresent,
            updateValue = { viewModel.setAmbulancePresent(it) }
        )

        // ELECTRIC COMPANY PRESENT
        SwitchWithTextField(
            checked = uiState.electricCompanyCheck,
            onChange = { viewModel.setElectricCompanyCheck(it) },
            labelResource = R.string.electric_company_present,
            value = reportState.electricCompanyPresent,
            updateValue = { viewModel.setElectricCompanyPresent(it) }
        )

        // TRANSIT POLICE PRESENT
        SwitchWithTextField(
            checked = uiState.transitPoliceCheck,
            onChange = { viewModel.setTransitPoliceCheck(it) },
            labelResource = R.string.transit_police_present,
            value = reportState.transitPolicePresent,
            updateValue = { viewModel.setTransitPolicePresent(it) }
        )

        // NOTES
        FormSubHeader(textResource = R.string.notes)
        TextField(
            value = reportState.notes ?: "",
            onValueChange = { viewModel.setNotes(it) },
            isError = reportState.notes == null,
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

        // VICTIM COUNT
        FormSubHeaderWithArgs(
            textResource = (R.string.victim_count_with_count),
            formatArgs = reportState.victimInfo.size
        )
        Row(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.full_field_width)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Row() {
                FormButton(
                    onClick = {
                        if (reportState.victimInfo.size - 1 >= 0) {
                            viewModel.setVictimCount(-1)
                        }
                    },
                    labelResource = R.string.remove
                )
                FormButton(
                    onClick = { viewModel.setVictimCount(1) },
                    labelResource = R.string.add
                )
            }
        }

        // VICTIM INFO

        reportState.victimInfo.forEachIndexed { index, _ ->
            val optionsVictimCodes: List<VictimCodes> = VictimCodeData.getCode()
            HorizontalDivider(
                modifier = Modifier
                .padding(dimensionResource(id = R.dimen.moderate_spacing))
            )
            // STATUS CODE
            DropDownTextField(
                displayValue = reportState.victimInfo[index].statusCode,
                updateDataValue = { viewModel.setVictimStatusByIndex(index, it) },
                optionsVictimCodes = optionsVictimCodes,
                labelResource = R.string.victim_status_with_arg,
                labelArg = index + 1
            )
            // NAME
            BasicTextField(
                value = reportState.victimInfo[index].name,
                updateValue = { viewModel.setVictimNameByIndex(index, it) },
                labelResource = R.string.victim_name_with_arg,
                labelArg = index + 1
            )
            // AGE
            BasicTextField(
                value = reportState.victimInfo[index].age,
                updateValue = { viewModel.setVictimAgeByIndex(index, it) },
                labelResource = R.string.victim_age_with_arg,
                labelArg = index + 1
            )
            // IDENTIFICATION
            BasicTextField(
                value = reportState.victimInfo[index].identification,
                updateValue = { viewModel.setVictimIdentificationByIndex(index, it) },
                labelResource = R.string.victim_identification_with_arg,
                labelArg = index + 1
            )
        }
        
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thick_spacing)))
    }
}