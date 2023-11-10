package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
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
    if (openDateDialog.value) {
        DatePickerDialog(
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