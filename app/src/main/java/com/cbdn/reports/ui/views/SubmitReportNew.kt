package com.cbdn.reports.ui.views


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonColors
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cbdn.reports.R
import com.cbdn.reports.data.EmergencyCodeData
import com.cbdn.reports.data.TruckData
import com.cbdn.reports.ui.viewmodel.SubmitReportNewViewModel
import convertMillisToDateTime
import java.util.Calendar


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

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val now: Calendar = Calendar.getInstance()

        // TEMP VARIABLES FOR HOLDING DATA INPUT
        var respondingTruck: String? by remember { mutableStateOf(null) }
        var commandingOfficer: String? by remember { mutableStateOf(null) }
        var datetimeDispatch: Long by remember { mutableStateOf(now.timeInMillis) }
        var emergencyCode: String? by remember { mutableStateOf(null) }
        var location: String? by remember { mutableStateOf(null) }

        Text(
            text = stringResource(id = R.string.submit_report_dispatch_details_header),
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.full_field_width))
                .padding(top = dimensionResource(id = R.dimen.field_top_padding)),
            textAlign = TextAlign.Left
        )
        // RESPONDING TRUCK
        val truckOptions = TruckData.getTrucks()
        var expandedTrucks by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expandedTrucks,
            onExpandedChange = { expandedTrucks = !expandedTrucks },
        ) {
            TextField(
                readOnly = true,
                value = respondingTruck ?: "",
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
                .width(dimensionResource(id = R.dimen.full_field_width)),
//            colors = TextFieldColors()
        )

        // DATETIME OF DISPATCH
        Text(
            text = stringResource(id = R.string.date_and_time_of_dispatch),
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.full_field_width))
                .padding(top = dimensionResource(id = R.dimen.field_top_padding)),
            textAlign = TextAlign.Left
        )
        // DATETIME
        // DATE DIALOG VARIABLES
        val openDateDialog = remember { mutableStateOf(false) }
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = now.timeInMillis
        )
        // TIME  DIALOG VARIABLES
        val openTimeDialog = remember { mutableStateOf(false) }
        val timePickerState = rememberTimePickerState(
            initialHour = now.get(Calendar.HOUR_OF_DAY), initialMinute = now.get(Calendar.MINUTE)
        )
        Row() {
            Button(
                onClick = { openDateDialog.value = true },
                shape = RectangleShape,
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.half_field_width))
                    .padding(horizontal = 10.dp)
            ) {
                Text(text = stringResource(id = R.string.change_date))
            }
            Button(
                onClick = { openTimeDialog.value = true },
                shape = RectangleShape,
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.half_field_width))
                    .padding(horizontal = 10.dp)
            ) {
                Text(text = stringResource(id = R.string.change_time))
            }
        }
        TextField(
            readOnly = true,
            value = convertMillisToDateTime(datetimeDispatch) ?: "",
            onValueChange = {},
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.full_field_width))
        )
        if (openDateDialog.value) {
            val confirmEnabled =
                derivedStateOf { datePickerState.selectedDateMillis != null }
            DatePickerDialog(
                onDismissRequest = { openDateDialog.value = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val newDate = Calendar.getInstance()
                            newDate.timeInMillis = datePickerState.selectedDateMillis ?: 0
                            now.set(newDate.get(Calendar.YEAR),newDate.get(Calendar.MONTH),newDate.get(Calendar.DAY_OF_MONTH))
                            datetimeDispatch = now.timeInMillis
                            openDateDialog.value = false
                        },
                        enabled = confirmEnabled.value
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            openDateDialog.value = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(
                    state = datePickerState
                )
            }
        }
        if (openTimeDialog.value) {
            DatePickerDialog(
                onDismissRequest = { openTimeDialog.value = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            now.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                            now.set(Calendar.MINUTE, timePickerState.minute)
                            datetimeDispatch = now.timeInMillis
                            openTimeDialog.value = false
                        },
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            openTimeDialog.value = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            ) {
                TimeInput(
                    state = timePickerState,
                    modifier = Modifier
                )
            }
        }

        // EMERGENCY CODE
        Text(
            text = stringResource(id = R.string.select_code_category),
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
        var selectedOptionText: String? by remember { mutableStateOf(null) }

        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.full_field_width))
        ) {
            codeCategories.forEachIndexed { index, label ->
                SegmentedButton(
                    selected = index == categoryIndex,
                    onClick = {
                        categoryIndex = index
                        selectedOptionText = EmergencyCodeData.getCode(codeCategories[categoryIndex])[0].name
                        emergencyCode = EmergencyCodeData.getCode(codeCategories[categoryIndex])[0].code
                              },
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = codeCategories.size),
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
        ExposedDropdownMenuBox(
            expanded = expandedCodes,
            onExpandedChange = { expandedCodes = !expandedCodes },
        ) {
            TextField(
                readOnly = true,
                value = selectedOptionText ?: "",
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

@Composable
fun SubmitReportNewPreview() {
    SubmitReportNew(viewModel = SubmitReportNewViewModel())
}
