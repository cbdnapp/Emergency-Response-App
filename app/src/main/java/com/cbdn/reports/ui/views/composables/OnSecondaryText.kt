package com.cbdn.reports.ui.views.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun OnSecondaryText(
    textResource: Int
) {
    Text(
        text = stringResource(id = textResource),
        color = MaterialTheme.colorScheme.onSecondary
    )
}