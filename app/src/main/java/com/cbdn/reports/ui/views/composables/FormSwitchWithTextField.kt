package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.cbdn.reports.R

@Composable
fun FormSwitchWithTextField(
    labelResource: Int,
    checked: Boolean,
    onChange: (Boolean) -> Unit,
    value: String?,
    updateValue: (String) -> Unit
) {
    if (value == null && !checked) updateValue("")
    Row(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.full_field_width))
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column {
            FormSubHeader(textResource = labelResource)
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Switch(
                    checked = checked,
                    onCheckedChange = { onChange(it) }
                )
                Spacer(modifier = Modifier.padding(
                    dimensionResource(id = R.dimen.thin_spacing)
                ))
                if (checked) {
                    TextField(
                        value = value ?: "",
                        onValueChange = updateValue,
                        isError = value.isNullOrEmpty(),
                        label = { Text(text = stringResource(id = R.string.unit_id)) },
                        trailingIcon = {
                            if (value != null) {
                                if (value.isEmpty())
                                    Icon(
                                        imageVector = Icons.Rounded.Info,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.error
                                    )
                            }
                        },
                        singleLine = true,
                        )
                }
            }
        }
    }
}