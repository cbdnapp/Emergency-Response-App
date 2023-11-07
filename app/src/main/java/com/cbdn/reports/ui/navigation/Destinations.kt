package com.cbdn.reports.ui.navigation

import androidx.annotation.StringRes
import com.cbdn.reports.R

enum class Destinations(
    @StringRes val title: Int
    ) {
    AppMenu(
        title = R.string.app_name
    ),
    SubmitReportNew(
        title = R.string.submit_report_new_header
    ),
    SubmitReportFinish(
        title = R.string.submit_report_finish_header
    ),
    SubmitReportAmend(
        title = R.string.submit_report_amend_header
    ),
    ViewReportFilter(
        title = R.string.view_report_filter_header
    ),
    ViewReportStatistics(
        title = R.string.view_report_statistics_header
    )
}