package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.cbdn.reports.R
import com.cbdn.reports.data.datamodel.Report
import convertMillisToDateTime

@Composable
fun LazyColumnOfReports(
    items: List<Pair<String, Report>>,
    modifier: Modifier
) {
    LazyColumn {
        items(items = items) { report ->
            Card(
                colors = CardDefaults.cardColors(contentColor = MaterialTheme.colorScheme.primary),
            ) {
                Column(
                    modifier = modifier
                        .padding(dimensionResource(id = R.dimen.moderate_spacing))
                ) {
                    CardRow(
                        header = stringResource(id = R.string.report_id),
                        data = report.first,
                        modifier = modifier
                    )
                    CardRow(
                        header = stringResource(id = R.string.responding_truck),
                        data = report.second.respondingTruck.toString(),
                        modifier = modifier
                    )
                    CardRow(
                        header = stringResource(id = R.string.commanding_officer),
                        data = report.second.commandingOfficer.toString(),
                        modifier = modifier
                    )
                    convertMillisToDateTime(report.second.datetimeDispatch)?.let {
                        CardRow(
                            header = stringResource(id = R.string.date_and_time_of_dispatch),
                            data = it,
                            modifier = modifier
                        )
                    }
                    CardRow(
                        header = stringResource(id = R.string.emergency_code),
                        data = report.second.emergencyCode.toString(),
                        modifier = modifier
                    )
                    HeaderText(text = stringResource(id = R.string.location))
                    DataText(text = report.second.location.toString())
                    convertMillisToDateTime(report.second.datetimeArrival)?.let {
                        CardRow(
                            header = stringResource(id = R.string.date_and_time_of_arrival),
                            data = it,
                            modifier = modifier
                        )
                    }
                    CardRow(
                        header = stringResource(id = R.string.police_present),
                        data = report.second.policePresent.toString(),
                        modifier = modifier
                    )
                    CardRow(
                        header = stringResource(id = R.string.ambulance_present),
                        data = report.second.ambulancePresent.toString(),
                        modifier = modifier
                    )
                    CardRow(
                        header = stringResource(id = R.string.electric_company_present),
                        data = report.second.electricCompanyPresent.toString(),
                        modifier = modifier
                    )
                    CardRow(
                        header = stringResource(id = R.string.transit_police_present),
                        data = report.second.transitPolicePresent.toString(),
                        modifier = modifier
                    )
                    CardRow(
                        header = stringResource(id = R.string.victim_info),
                        data = stringResource(
                            id = R.string.victim_count_with_count,
                            report.second.victimInfo.size
                        ),
                        modifier = modifier
                    )
                    report.second.victimInfo.forEachIndexed{ index, victim ->
                        Column(
                            modifier = modifier
                                .padding(dimensionResource(id = R.dimen.thin_spacing))
                        ) {
                            HeaderText(stringResource(
                                id = R.string.victim_number_with_data,
                                index+1)
                            )
                            CardRow(
                                header = stringResource(id = R.string.victim_status_code),
                                data = victim.statusCode,
                                modifier = modifier
                            )
                            CardRow(
                                header = stringResource(id = R.string.victim_name),
                                data = victim.name,
                                modifier = modifier
                            )
                            CardRow(
                                header = stringResource(id = R.string.victim_age),
                                data = victim.age,
                                modifier = modifier
                            )
                            CardRow(
                                header = stringResource(id = R.string.victim_identification),
                                data = victim.identification,
                                modifier = modifier
                            )
                        }
                    }
                    HeaderText(text = stringResource(id = R.string.notes))
                    DataText(text = report.second.notes.toString())
                    convertMillisToDateTime(report.second.datetimeReturn)?.let {
                        CardRow(
                            header = stringResource(id = R.string.date_and_time_of_return),
                            data = it,
                            modifier = modifier
                        )
                    }
                    CardRow(
                        header = stringResource(id = R.string.report_writer),
                        data = report.second.reportWriter.toString(),
                        modifier = modifier
                    )
                }
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun CardRow(
    header: String,
    data: String,
    modifier: Modifier
){
    Row(
        modifier=modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        HeaderText(header)
        DataText(data)
    }
}

@Composable
fun HeaderText(
    text: String
) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold
    )
}
@Composable
fun DataText(
    text: String
) {
    Text(text = text)
}