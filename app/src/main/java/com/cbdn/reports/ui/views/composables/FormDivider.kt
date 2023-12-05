package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.cbdn.reports.R

@Composable
fun FormDivider() {
    HorizontalDivider(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.full_field_width))
            .padding(vertical = dimensionResource(id = R.dimen.thin_spacing))
    )
}