package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.cbdn.reports.R

@Composable
fun FormHeader (
    textResource: Int
) {
    Text(
        text = stringResource(id = textResource),
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.full_field_width))
            .padding(top = dimensionResource(id = R.dimen.moderate_spacing)),
        style = MaterialTheme.typography.headlineLarge
    )
}