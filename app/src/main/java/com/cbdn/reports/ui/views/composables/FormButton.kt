package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cbdn.reports.R

@Composable
fun FormButton(
    onClick: () -> Unit,
    labelResource: Int
) {
    Button(
        onClick = onClick,
        shape = RectangleShape,
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.half_field_width))
            .padding(horizontal = 10.dp)
    ) {
        Text(text = stringResource(id = labelResource))
    }
}