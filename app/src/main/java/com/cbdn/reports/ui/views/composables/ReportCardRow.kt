package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ReportCardRow(
    header: String,
    data: String,
    modifier: Modifier
){
    Row(
        modifier=modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ReportHeaderText(header)
        ReportDataText(data)
    }
}