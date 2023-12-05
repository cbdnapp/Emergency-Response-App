package com.cbdn.reports.ui.views.composables

import DialogHeader
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cbdn.reports.R
import com.cbdn.reports.data.VictimCodes

@Composable
fun FormAddVictimDialog(
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
                .background(color = MaterialTheme.colorScheme.background)
                .padding(dimensionResource(id = R.dimen.thick_spacing)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DialogHeader(textResource = R.string.victim_details)

            // STATUS CODE
            FormDropDownTextField(
                displayValue = statusCode,
                updateDataValue = setStatusCode,
                optionsVictimCodes = optionsVictimCodes,
                labelResource = R.string.victim_status_code
            )
            // NAME
            FormOneLineTextField(
                value = name,
                updateValue = setName,
                labelResource = R.string.victim_name
            )
            // AGE
            FormOneLineTextField(
                value = age,
                updateValue = setAge,
                labelResource = R.string.victim_age
            )
            // IDENTIFICATION
            FormOneLineTextField(
                value = identification,
                updateValue = setIdentification,
                labelResource = R.string.victim_identification
            )
            Row() {
                FormButton(
                    onClick = onDismiss,
                    labelResource = R.string.cancel
                )
                if (victimEditIndex == null) {
                    FormButton(
                        onClick = onConfirmation,
                        labelResource = R.string.ok
                    )
                } else {
                    FormButton(
                        onClick = onEdit,
                        labelResource = R.string.update
                    )
                }
            }
        }
    }
}
