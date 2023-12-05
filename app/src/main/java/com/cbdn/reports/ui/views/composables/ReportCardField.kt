package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.cbdn.reports.R

@Composable
fun ReportCardField(
    header: String,
    data: String,
    modifier: Modifier
){
    Column(
        modifier=modifier,
        horizontalAlignment = Alignment.Start
    ) {
        ReportHeaderText(header)
        ReportDataText(data)
        HorizontalDivider()
    }
}

@Composable
fun ReportDataText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier
            .padding(start = dimensionResource(id = R.dimen.thin_spacing))
    )
}

@Composable
fun ReportHeaderText(
    text: String
) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold
    )
}