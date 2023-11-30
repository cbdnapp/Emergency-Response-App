package com.cbdn.reports.ui.views.newreport


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cbdn.reports.R
import com.cbdn.reports.data.EmergencyCodeData
import com.cbdn.reports.data.TruckData
import com.cbdn.reports.ui.viewmodel.AppViewModel
import com.cbdn.reports.ui.views.composables.BasicTextField
import com.cbdn.reports.ui.views.composables.DateTimeSelection
import com.cbdn.reports.ui.views.composables.DropDownTextField
import com.cbdn.reports.ui.views.composables.FormDivider
import com.cbdn.reports.ui.views.composables.FormHeader
import com.cbdn.reports.ui.views.composables.FormSubHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DispatchDetails(
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
        FormHeader(textResource = R.string.submit_dispatch_details_header)

        // RESPONDING TRUCK
        FormSubHeader(textResource = R.string.responding_truck)
        DropDownTextField(
            displayValue = reportState.respondingTruck,
            updateDataValue = { viewModel.setRespondingTruck(it) },
            optionsTrucks = TruckData.getTrucks(),
            labelResource = R.string.select_truck_id
        )
        FormDivider()

        // COMMANDING OFFICER
        FormSubHeader(textResource = R.string.commanding_officer)
        BasicTextField(
            value = reportState.commandingOfficer,
            updateValue = { viewModel.setCommandingOfficer(it) },
            labelResource = R.string.enter_officers_name
        )
        FormDivider()

        // DATETIME OF DISPATCH
        FormSubHeader(textResource = R.string.date_and_time_of_dispatch)
        DateTimeSelection(
            displayValue = reportState.datetimeDispatch,
            updateDatetime = { viewModel.setDatetimeDispatch(it)}
        )
        FormDivider()

        // EMERGENCY CODE
        FormSubHeader(textResource = R.string.emergency_code)
        val codeCategories = EmergencyCodeData.getCategories()
        val optionsEmergencyCodes = EmergencyCodeData.getCode(codeCategories[uiState.categoryIndex])
        FormSubHeader(textResource = R.string.select_code_category)
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.full_field_width))
                .padding(bottom = dimensionResource(id = R.dimen.moderate_spacing)),
        ) {
            codeCategories.forEachIndexed { index, label ->
                SegmentedButton(
                    selected = index == uiState.categoryIndex,
                    onClick = { viewModel.setCategoryIndex(index) },
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = codeCategories.size),
                    colors = SegmentedButtonDefaults.colors(
                        activeContainerColor = MaterialTheme.colorScheme.onPrimary,
                        activeContentColor = MaterialTheme.colorScheme.primary,
                        inactiveContainerColor = MaterialTheme.colorScheme.primary,
                        inactiveContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(text = label.category)
                }
            }
        }
        DropDownTextField(
            displayValue = reportState.emergencyCode,
            updateDataValue = { viewModel.setEmergencyCode(it) },
            optionsEmergencyCodes = optionsEmergencyCodes,
            labelResource = R.string.select_emergency_code_id
        )
        FormDivider()
    }
}
