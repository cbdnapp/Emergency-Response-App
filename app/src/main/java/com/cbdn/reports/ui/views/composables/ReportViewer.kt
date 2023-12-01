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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.cbdn.reports.R
import com.cbdn.reports.data.datamodel.Report
import convertMillisToDateTime

@Composable
fun ReportViewer(
    reportData: Pair<String, Report>,
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
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(dimensionResource(id = R.dimen.thin_spacing))
                .verticalScroll(rememberScrollState()),
        ) {
            val reportId = reportData.first
            val report = reportData.second
            ReportCardRow(
                header = stringResource(id = R.string.responding_truck),
                data = report.respondingTruck.toString(),
                modifier = modifier
            )
            ReportCardRow(
                header = stringResource(id = R.string.commanding_officer),
                data = report.commandingOfficer.toString(),
                modifier = modifier
            )
            convertMillisToDateTime(report.datetimeDispatch)?.let {
                ReportCardRow(
                    header = stringResource(id = R.string.date_and_time_of_dispatch),
                    data = it,
                    modifier = modifier
                )
            }
            ReportCardRow(
                header = stringResource(id = R.string.emergency_code),
                data = report.emergencyCode.toString(),
                modifier = modifier
            )
            ReportHeaderText(text = stringResource(id = R.string.location))
            ReportDataText(
                text = report.location.toString(),
                modifier = Modifier.padding(dimensionResource(id = R.dimen.thin_spacing))
            )
            convertMillisToDateTime(report.datetimeArrival)?.let {
                ReportCardRow(
                    header = stringResource(id = R.string.date_and_time_of_arrival),
                    data = it,
                    modifier = modifier
                )
            }
            ReportCardRow(
                header = stringResource(id = R.string.police_present),
                data = report.policePresent.toString(),
                modifier = modifier
            )
            ReportCardRow(
                header = stringResource(id = R.string.ambulance_present),
                data = report.ambulancePresent.toString(),
                modifier = modifier
            )
            ReportCardRow(
                header = stringResource(id = R.string.electric_company_present),
                data = report.electricCompanyPresent.toString(),
                modifier = modifier
            )
            ReportCardRow(
                header = stringResource(id = R.string.transit_police_present),
                data = report.transitPolicePresent.toString(),
                modifier = modifier
            )
            ReportCardRow(
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
                        .padding(dimensionResource(id = R.dimen.thin_spacing))
                ) {
                    ReportHeaderText(
                        stringResource(
                            id = R.string.victim_number_with_data,
                            index + 1
                        )
                    )
                    ReportCardRow(
                        header = stringResource(id = R.string.victim_status_code),
                        data = victim.statusCode,
                        modifier = modifier
                    )
                    ReportCardRow(
                        header = stringResource(id = R.string.victim_name),
                        data = victim.name,
                        modifier = modifier
                    )
                    ReportCardRow(
                        header = stringResource(id = R.string.victim_age),
                        data = victim.age,
                        modifier = modifier
                    )
                    ReportCardRow(
                        header = stringResource(id = R.string.victim_identification),
                        data = victim.identification,
                        modifier = modifier
                    )
                }
            }
            ReportHeaderText(text = stringResource(id = R.string.notes))
            ReportDataText(
                text = report.notes.toString(),
                modifier = Modifier.padding(dimensionResource(id = R.dimen.thin_spacing))
            )
            convertMillisToDateTime(report.datetimeReturn)?.let {
                ReportCardRow(
                    header = stringResource(id = R.string.date_and_time_of_return),
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
                header = stringResource(id = R.string.report_id),
                data = reportId,
                modifier = modifier
            )
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
                        labelResource = R.string.delete_report,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(dimensionResource(id = R.dimen.thin_spacing))
                    )
                }
                if (showFinish) {
                    OnPrimaryTextButton(
                        onClick = clickFinish,
                        labelResource = R.string.finish_report,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(dimensionResource(id = R.dimen.thin_spacing))
                    )
                }
                if (showAmend) {
                    OnPrimaryTextButton(
                        onClick = clickAmend,
                        labelResource = R.string.amend_report,
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