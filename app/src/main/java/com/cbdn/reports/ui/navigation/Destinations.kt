package com.cbdn.reports.ui.navigation

import androidx.annotation.StringRes
import com.cbdn.reports.R

enum class Destinations(
    @StringRes val title: Int
    ) {
    AppMenu(
        title = R.string.app_name
    ),
    SubmitNew(
        title = R.string.submit_new_header
    ),
        SubmitNewDispatch(
            title = R.string.submit_new_header
        ),
        SubmitNewLocation(
            title = R.string.submit_new_header
        ),
        SubmitNewOnScene(
            title = R.string.submit_new_header
        ),
        SubmitNewSubmit(
            title = R.string.submit_new_header
        ),
    SubmitFinish(
        title = R.string.submit_finish_header
    ),
    SubmitAmend(
        title = R.string.submit_amend_header
    ),
    ViewFilter(
        title = R.string.view_filter_header
    ),
    ViewStatistics(
        title = R.string.view_statistics_header
    )
}