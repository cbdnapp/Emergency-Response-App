package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.cbdn.reports.R

@Composable
fun BasicTextField (
    value: String?,
    updateValue: (String) -> Unit,
    labelResource: Int,
) {
    TextField(
        value = value ?: "",
        onValueChange = updateValue,
        isError = value.isNullOrEmpty(),
        label = { Text(text = stringResource(id = labelResource)) },
        singleLine = true,
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.full_field_width)),
        trailingIcon = {
            Text(
                text = stringResource(id = R.string.required),
                modifier = Modifier.padding(dimensionResource(id = R.dimen.thin_spacing))
            )
        },

    )
}