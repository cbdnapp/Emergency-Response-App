package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.cbdn.reports.R

@Composable
fun FormButton(
    onClick: () -> Unit,
    labelResource: Int,
    modifier: Modifier = Modifier
) {
    TextButton(
        shape = RectangleShape,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.thin_spacing))
    ) {
        Text(text = stringResource(id = labelResource))
    }
}