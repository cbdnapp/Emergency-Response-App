package com.cbdn.reports.ui.views.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ReportHeaderText(
    text: String
) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold
    )
}