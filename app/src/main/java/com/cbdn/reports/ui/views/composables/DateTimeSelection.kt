package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import com.cbdn.reports.R
import convertMillisToDateTime
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimeSelection (
    displayValue: Long?,
    updateDatetime: (Long) -> Unit
) {
    val now: Calendar = Calendar.getInstance()
    now.timeInMillis = displayValue ?: now.timeInMillis
    if (displayValue == null) updateDatetime(now.timeInMillis)

    val openDateDialog = remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = now.timeInMillis
    )
    val openTimeDialog = remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState(
        initialHour = now.get(Calendar.HOUR_OF_DAY),
        initialMinute = now.get(Calendar.MINUTE)
    )

    Row(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.full_field_width))
    ) {
        FormButton(
            onClick = { openDateDialog.value = true },
            labelResource = R.string.change_date,
            modifier = Modifier.weight(1f)
        )
        FormButton(
            onClick = { openTimeDialog.value = true },
            labelResource = R.string.change_time,
            modifier = Modifier.weight(1f)
        )
    }
    if (openDateDialog.value) {
        DatePickerDialog(
            modifier = Modifier
                .border(
                    dimensionResource(id = R.dimen.thin_spacing),
                    MaterialTheme.colorScheme.tertiaryContainer
                )
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            properties = DialogProperties(usePlatformDefaultWidth = false),
            shape = RectangleShape,
            onDismissRequest = { openDateDialog.value = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val newDate = Calendar.getInstance()
                        newDate.timeInMillis = datePickerState.selectedDateMillis ?: 0
                        now.set(
                            newDate.get(Calendar.YEAR),
                            newDate.get(Calendar.MONTH),
                            newDate.get(Calendar.DAY_OF_MONTH)
                        )
                        updateDatetime(now.timeInMillis)
                        openDateDialog.value = false
                    },
                ) {
                    Text(stringResource(id = R.string.ok))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDateDialog.value = false
                    }
                ) {
                    Text(stringResource(id = R.string.cancel))
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
            modifier = Modifier
                .border(
                    dimensionResource(id = R.dimen.thin_spacing),
                    MaterialTheme.colorScheme.tertiaryContainer
                )
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            properties = DialogProperties(usePlatformDefaultWidth = false),
            shape = RectangleShape,
            onDismissRequest = { openTimeDialog.value = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        now.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                        now.set(Calendar.MINUTE, timePickerState.minute)
                        updateDatetime(now.timeInMillis)
                        openTimeDialog.value = false
                    },
                ) {
                    Text(stringResource(id = R.string.ok))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openTimeDialog.value = false
                    }
                ) {
                    Text(stringResource(id = R.string.cancel))
                }
            }
        ) {
            TimeInput(
                state = timePickerState,
                modifier = Modifier
            )
        }
    }
    TextField(
        readOnly = true,
        value = convertMillisToDateTime(displayValue) ?: "",
        onValueChange = {},
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.full_field_width))
    )
}