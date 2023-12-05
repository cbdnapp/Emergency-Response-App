package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import com.cbdn.reports.R
import convertMillisToDate
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangeSelection (
    fromValue: Long?,
    toValue: Long?,
    changeFrom: (Long) -> Unit,
    changeTo: (Long) -> Unit,
) {
    val now: Calendar = Calendar.getInstance()
    now.timeInMillis = toValue ?: now.timeInMillis
    now.set(Calendar.HOUR, 11)
    now.set(Calendar.MINUTE, 59)
    now.set(Calendar.SECOND, 59)
    now.set(Calendar.AM_PM, 1)
    if (toValue == null) changeTo(now.timeInMillis)

    val openDateDialog = remember { mutableStateOf(false) }
    val fromFlag = remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = now.timeInMillis
    )

    Row(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.full_field_width))
    ) {
        FormButton(
            onClick = {
                fromFlag.value = true
                openDateDialog.value = true
                      },
            labelResource = R.string.from_date,
            args = convertMillisToDate(fromValue) ?: "",
            modifier = Modifier.weight(1f)
        )
        FormButton(
            onClick = {
                fromFlag.value = false
                openDateDialog.value = true
                      },
            labelResource = R.string.to_date,
            args = convertMillisToDate(toValue) ?: "",
            modifier = Modifier.weight(1f)
        )
    }
    if (openDateDialog.value) {
        DatePickerDialog(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.thick_spacing))
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
                            newDate.get(Calendar.DAY_OF_MONTH) + 1
                        )
                        if (fromFlag.value) {
                            now.set(Calendar.HOUR, 0)
                            now.set(Calendar.MINUTE, 0)
                            now.set(Calendar.SECOND, 0)
                            now.set(Calendar.AM_PM, 0)
                            changeFrom(now.timeInMillis)
                        } else {
                            now.set(Calendar.HOUR, 11)
                            now.set(Calendar.MINUTE, 59)
                            now.set(Calendar.SECOND, 59)
                            now.set(Calendar.AM_PM, 1)
                            changeTo(now.timeInMillis)
                        }
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
}