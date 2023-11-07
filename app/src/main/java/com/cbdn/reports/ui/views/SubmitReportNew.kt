package com.cbdn.reports.ui.views


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cbdn.reports.R
import com.cbdn.reports.data.EmergencyCodeData
import com.cbdn.reports.data.TruckData
import com.cbdn.reports.ui.viewmodel.SubmitReportNewViewModel
import convertMillisToDate


@Composable
fun SubmitReportNew(
    viewModel: SubmitReportNewViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    DispatchDetails()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DispatchDetails(

){
    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.submit_report_dispatch_details_header),
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.full_field_width))
                .padding(top = dimensionResource(id = R.dimen.field_top_padding)),
            textAlign = TextAlign.Left
        )
        // TEMP VARIABLES FOR HOLDING DATA INPUT
        var respondingTruck: String? by remember { mutableStateOf(null) }
        var commandingOfficer: String? by remember { mutableStateOf(null) }
        var dateDispatch: String? by remember { mutableStateOf(null) }
        var timeDispatch: String? by remember { mutableStateOf(null) }
        var emergencyCode: String? by remember { mutableStateOf(null) }
        var location: String? by remember { mutableStateOf(null) }

        // RESPONDING TRUCK
        val truckOptions = TruckData.getTrucks()
        var expandedTrucks by remember {mutableStateOf(false)}
        var selectedTruckText by remember { mutableStateOf(truckOptions[0].code) }
        ExposedDropdownMenuBox(
            expanded = expandedTrucks,
            onExpandedChange = { expandedTrucks = !expandedTrucks },
        ) {
            TextField(
                readOnly = true,
                value = selectedTruckText,
                onValueChange = {},
                label = { Text(stringResource(id = R.string.responding_truck)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTrucks) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier
                    .menuAnchor()
                    .width(dimensionResource(id = R.dimen.full_field_width)),
            )
            ExposedDropdownMenu(
                expanded = expandedTrucks,
                onDismissRequest = { expandedTrucks = false },
            ) {
                truckOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption.code) },
                        onClick = {
                            selectedTruckText = selectionOption.code
                            respondingTruck = selectionOption.code
                            expandedTrucks = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }

        // COMMANDING OFFICER
        TextField(
            value = commandingOfficer ?: "",
            onValueChange = { commandingOfficer = it },
            label = { Text(text = stringResource(id = R.string.commanding_officer)) },
            singleLine = true,
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.full_field_width))
        )

        // DATETIME OF DISPATCH
        Text(
            text = stringResource(id = R.string.datetime_of_dispatch),
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.full_field_width))
                .padding(top = dimensionResource(id = R.dimen.field_top_padding)),
            textAlign = TextAlign.Left
        )
        Row(modifier = Modifier) {
            // DATE
            val openDialog = remember { mutableStateOf(false) }
            val datePickerState = rememberDatePickerState()
            TextField(
                readOnly = true,
                value = dateDispatch ?: "",
                onValueChange = {},
                label = { Text(text = stringResource(id = R.string.date)) },
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.half_field_width))
                    .clickable { openDialog.value = true },
                enabled = false
            )
            if (openDialog.value) {
                val confirmEnabled =
                    derivedStateOf { datePickerState.selectedDateMillis != null }
                DatePickerDialog(
                    onDismissRequest = { openDialog.value = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                openDialog.value = false
                                dateDispatch = convertMillisToDate(datePickerState.selectedDateMillis)
                            },
                            enabled = confirmEnabled.value
                        ) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                openDialog.value = false
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                ) {
                    DatePicker(
                        state = datePickerState,
                    )
                }
            }
            // TIME
            TextField(
                readOnly = true,
                value = timeDispatch ?: "",
                onValueChange = { timeDispatch = it },
                label = { Text(text = stringResource(id = R.string.time)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.half_field_width))
            )
        }

        // EMERGENCY CODE
        Text(
            text = stringResource(id = R.string.emergency_code),
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.full_field_width))
                .padding(top = dimensionResource(id = R.dimen.field_top_padding)),
            textAlign = TextAlign.Left
        )
        // SingleChoiceSegmentedButtonRow VARIABLES
        val codeCategories = EmergencyCodeData.getCategories()
        var categoryIndex by remember { mutableStateOf(0) }
        // ExposedDropdownMenuBox VARIABLES
        val codeOptions = EmergencyCodeData.getCode(codeCategories[categoryIndex])
        var expandedCodes by remember {mutableStateOf(false)}
        var selectedOptionText by remember { mutableStateOf(codeOptions[0].name) }

        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
        ) {
            codeCategories.forEachIndexed { index, label ->
                SegmentedButton(
                    selected = index == categoryIndex,
                    onClick = {
                        categoryIndex = index
                        selectedOptionText = EmergencyCodeData.getCode(codeCategories[categoryIndex])[0].name
                        emergencyCode = EmergencyCodeData.getCode(codeCategories[categoryIndex])[0].code
                              },
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = codeCategories.size)
                ) {
                    Text(text = label.category)
                }
            }
        }
        ExposedDropdownMenuBox(
            expanded = expandedCodes,
            onExpandedChange = { expandedCodes = !expandedCodes },
        ) {
            TextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = {},
                label = { Text(stringResource(id = R.string.emergency_code)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCodes) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier
                    .menuAnchor()
                    .padding(top = 5.dp)
                    .width(dimensionResource(id = R.dimen.full_field_width)),
            )
            ExposedDropdownMenu(
                expanded = expandedCodes,
                onDismissRequest = { expandedCodes = false },
            ) {
                codeOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption.name) },
                        onClick = {
                            selectedOptionText = selectionOption.name
                            emergencyCode = selectionOption.code
                            expandedCodes = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }

        // LOCATION
        TextField(
            value = location ?: "",
            onValueChange = { location = it },
            label = { Text(text = stringResource(id = R.string.location)) },
            singleLine = true,
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.full_field_width))
        )
    }
}


@Composable
fun OnSceneDetails() {
    Text(text= stringResource(id = R.string.submit_report_on_scene_details_header))
}

@Composable
fun SubmittalDetails() {
    Text(text= stringResource(id = R.string.submit_report_submittal_details_header))
}

@Preview(showBackground = true)
@Composable
fun SubmitReportNewPreview() {
    SubmitReportNew(viewModel = SubmitReportNewViewModel())
}
