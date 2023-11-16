package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cbdn.reports.R
import com.cbdn.reports.data.VictimCodes

@Composable
fun AddVictimDialog(
    onConfirmation: () -> Unit,
    onDismiss: () -> Unit,
    onEdit: () -> Unit,
    optionsVictimCodes: List<VictimCodes>,
    statusCode: String,
    name: String,
    age: String,
    identification: String,
    setStatusCode: (String) -> Unit,
    setName: (String) -> Unit,
    setAge: (String) -> Unit,
    setIdentification: (String) -> Unit,
    victimEditIndex: Int?
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ){
        Column(
            modifier = Modifier
                .border(
                    dimensionResource(id = R.dimen.thin_spacing),
                    MaterialTheme.colorScheme.tertiaryContainer
                )
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            FormSubHeader(textResource = R.string.victim_details)

            // STATUS CODE
            DropDownTextField(
                displayValue = statusCode,
                updateDataValue = setStatusCode,
                optionsVictimCodes = optionsVictimCodes,
                labelResource = R.string.victim_status_code
            )
            // NAME
            BasicTextField(
                value = name,
                updateValue = setName,
                labelResource = R.string.victim_name
            )
            // AGE
            BasicTextField(
                value = age,
                updateValue = setAge,
                labelResource = R.string.victim_age
            )
            // IDENTIFICATION
            BasicTextField(
                value = identification,
                updateValue = setIdentification,
                labelResource = R.string.victim_identification
            )
            Row() {
                TextButton(onClick = onDismiss) {
                    Text(text = stringResource(id = R.string.cancel))
                }
                if (victimEditIndex == null) {
                    TextButton(onClick = onConfirmation) {
                        Text(text = stringResource(id = R.string.ok))
                    }
                } else {
                    TextButton(onClick = onEdit) {
                        Text(text = stringResource(id = R.string.update))
                    }
                }
            }
        }
    }
}
