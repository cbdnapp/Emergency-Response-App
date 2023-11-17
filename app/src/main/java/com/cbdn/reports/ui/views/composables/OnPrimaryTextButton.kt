package com.cbdn.reports.ui.views.composables

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun OnPrimaryTextButton(
    enabled: Boolean,
    onClick: () -> Unit,
    labelResource: Int,
    modifier: Modifier
) {
    TextButton (
        shape = RectangleShape,
        elevation = ButtonDefaults.buttonElevation(10.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimary,),
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = labelResource),
            color = MaterialTheme.colorScheme.primary
        )
    }
}