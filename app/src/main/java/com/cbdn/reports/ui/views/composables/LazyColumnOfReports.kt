package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.cbdn.reports.R
import com.cbdn.reports.data.datamodel.Report
import convertMillisToDateTime

@Composable
fun LazyColumnOfReports(
    items: List<Pair<String, Report>>,
    selectReport: (Int) -> Unit,
    modifier: Modifier
) {
    LazyColumn {
        itemsIndexed(items = items) { index, item ->
            val reportId = item.first
            val report = item.second
            Card(
                colors = CardDefaults.cardColors(contentColor = MaterialTheme.colorScheme.primary),
                shape = RectangleShape,
                modifier = Modifier
                    .clickable { selectReport(index) }
            ) {
                Row(
                    modifier = modifier
                        .padding(dimensionResource(id = R.dimen.moderate_spacing)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(.9f)
                    ) {
                        convertMillisToDateTime(report.datetimeDispatch)?.let {
                            ReportCardRow(
                                header = stringResource(id = R.string.date_and_time_of_dispatch),
                                data = it,
                                modifier = modifier
                            )
                        }
                        ReportCardRow(
                            header = stringResource(id = R.string.report_writer),
                            data = report.reportWriter.toString(),
                            modifier = modifier
                        )
                        ReportCardRow(
                            header = stringResource(id = R.string.responding_truck),
                            data = report.respondingTruck.toString(),
                            modifier = modifier
                        )
                        ReportCardRow(
                            header = stringResource(id = R.string.emergency_code),
                            data = report.emergencyCode.toString(),
                            modifier = modifier
                        )
                        ReportCardRow(
                            header = stringResource(id = R.string.location),
                            data = report.location.toString(),
                            modifier = modifier
                        )
                    }
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_read_more_24),
                        contentDescription = null,
                        modifier = Modifier
                            .weight(.1f)
                    )
                }
                HorizontalDivider()
            }
        }
    }
}