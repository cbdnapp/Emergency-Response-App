package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cbdn.reports.R
import com.cbdn.reports.data.datamodel.Report
import convertMillisToDateTime

@Composable
fun ReportViewer(
    report: Report,
    reportId: String,
    clickPrevious: () -> Unit,
    showDelete: Boolean = false,
    clickDelete: () -> Unit = {},
    showFinish: Boolean = false,
    clickFinish: () -> Unit = {},
    showAmend: Boolean = false,
    clickAmend: () -> Unit = {},
    modifier: Modifier
) {
    Scaffold(
        bottomBar = {
            ReportViewerBottomBar(
                clickPrevious = clickPrevious,
                showDelete = showDelete,
                clickDelete = clickDelete,
                showFinish = showFinish,
                clickFinish = clickFinish,
                showAmend = showAmend,
                clickAmend = clickAmend,
            )
        }
    ) { innerPadding ->
        Card(
            colors = CardDefaults.cardColors(contentColor = MaterialTheme.colorScheme.primary),
            shape = RectangleShape,
            elevation = CardDefaults.elevatedCardElevation(10.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(dimensionResource(id = R.dimen.thin_spacing)),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.moderate_spacing))
                    .verticalScroll(rememberScrollState())
            ) {
                ReportCardField(
                    header = stringResource(id = R.string.responding_truck),
                    data = report.respondingTruck.toString(),
                    modifier = modifier
                )
                ReportCardField(
                    header = stringResource(id = R.string.commanding_officer),
                    data = report.commandingOfficer.toString(),
                    modifier = modifier
                )
                ReportCardField(
                    header = stringResource(id = R.string.date_and_time_of_dispatch),
                    data = convertMillisToDateTime(report.datetimeDispatch) ?: null.toString(),
                    modifier = modifier
                )
                ReportCardField(
                    header = stringResource(id = R.string.emergency_code),
                    data = report.emergencyCode.toString(),
                    modifier = modifier
                )
                ReportCardField(
                    header = stringResource(id = R.string.location),
                    data = report.location.toString(),
                    modifier = modifier
                )
                ReportCardField(
                    header = stringResource(id = R.string.date_and_time_of_arrival),
                    data = convertMillisToDateTime(report.datetimeArrival) ?: null.toString(),
                    modifier = modifier
                )
                ReportCardField(
                    header = stringResource(id = R.string.police_present),
                    data = report.policePresent.toString().ifEmpty {
                        stringResource(id = R.string.no) },
                    modifier = modifier
                )
                ReportCardField(
                    header = stringResource(id = R.string.ambulance_present),
                    data = report.ambulancePresent.toString().ifEmpty {
                        stringResource(id = R.string.no) },
                    modifier = modifier
                )
                ReportCardField(
                    header = stringResource(id = R.string.electric_company_present),
                    data = report.electricCompanyPresent.toString().ifEmpty {
                        stringResource(id = R.string.no) },
                    modifier = modifier
                )
                ReportCardField(
                    header = stringResource(id = R.string.transit_police_present),
                    data = report.transitPolicePresent.toString().ifEmpty {
                        stringResource(id = R.string.no) },
                    modifier = modifier
                )
                ReportCardField(
                    header = stringResource(id = R.string.victim_info),
                    data = stringResource(
                        id = R.string.victim_count_with_count,
                        report.victimInfo.size
                    ),
                    modifier = modifier
                )
                report.victimInfo.forEachIndexed { index, victim ->
                    Column(
                        modifier = modifier
                            .padding(dimensionResource(id = R.dimen.moderate_spacing))
                    ) {
                        ReportHeaderText(
                            stringResource(
                                id = R.string.victim_number_with_data,
                                index + 1
                            )
                        )
                        ReportCardField(
                            header = stringResource(id = R.string.victim_status_code),
                            data = victim.statusCode,
                            modifier = modifier
                        )
                        ReportCardField(
                            header = stringResource(id = R.string.victim_name),
                            data = victim.name,
                            modifier = modifier
                        )
                        ReportCardField(
                            header = stringResource(id = R.string.victim_age),
                            data = victim.age,
                            modifier = modifier
                        )
                        ReportCardField(
                            header = stringResource(id = R.string.victim_identification),
                            data = victim.identification,
                            modifier = modifier
                        )
                    }
                }
                ReportCardField(
                    header = stringResource(id = R.string.notes),
                    data = report.notes.toString(),
                    modifier = modifier
                )
                ReportCardField(
                    header = stringResource(id = R.string.date_and_time_of_return),
                    data = convertMillisToDateTime(report.datetimeReturn) ?: null.toString(),
                    modifier = modifier
                )
                ReportCardField(
                    header = stringResource(id = R.string.report_writer),
                    data = report.reportWriter.toString(),
                    modifier = modifier
                )
                ReportCardField(
                    header = stringResource(id = R.string.report_id),
                    data = reportId,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun ReportViewerBottomBar(
    clickPrevious: () -> Unit,
    showDelete: Boolean,
    clickDelete: () -> Unit,
    showFinish: Boolean,
    clickFinish: () -> Unit,
    showAmend: Boolean,
    clickAmend: () -> Unit,
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        actions = {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ){
                OnPrimaryTextButton(
                    onClick = clickPrevious,
                    labelResource = R.string.previous,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(dimensionResource(id = R.dimen.thin_spacing))
                )
                if (showDelete) {
                    OnPrimaryTextButton(
                        onClick = clickDelete,
                        labelResource = R.string.delete,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(dimensionResource(id = R.dimen.thin_spacing))
                    )
                }
                if (showFinish) {
                    OnPrimaryTextButton(
                        onClick = clickFinish,
                        labelResource = R.string.finish,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(dimensionResource(id = R.dimen.thin_spacing))
                    )
                }
                if (showAmend) {
                    OnPrimaryTextButton(
                        onClick = clickAmend,
                        labelResource = R.string.amend,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(dimensionResource(id = R.dimen.thin_spacing))
                    )
                }
            }
        },
    )
}