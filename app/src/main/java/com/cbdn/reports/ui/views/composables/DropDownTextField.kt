package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.cbdn.reports.R
import com.cbdn.reports.data.EmergencyCodes
import com.cbdn.reports.data.Trucks
import com.cbdn.reports.data.VictimCodes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownTextField(
    displayValue: String?,
    updateDataValue: (String) -> Unit,
    optionsTrucks: List<Trucks>? = null,
    optionsEmergencyCodes: List<EmergencyCodes>? = null,
    optionsVictimCodes: List<VictimCodes>? = null,
    labelResource: Int,
    labelArg: Any = ""
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            readOnly = true,
            value = displayValue ?: "",
            onValueChange = {},
            isError = displayValue.isNullOrEmpty(),
            label = { Text(stringResource(id = labelResource, labelArg)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .menuAnchor()
                .width(dimensionResource(id = R.dimen.full_field_width)),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            optionsTrucks?.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption.code) },
                    onClick = {
                        updateDataValue(selectionOption.code)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
            optionsEmergencyCodes?.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption.name) },
                    onClick = {
                        updateDataValue(selectionOption.name)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
            optionsVictimCodes?.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption.name) },
                    onClick = {
                        updateDataValue(selectionOption.name)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}