package com.cbdn.reports.ui.views.finishreport

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.cbdn.reports.ui.viewmodel.FinishReportViewModel

@Composable
fun FinishReport(
    viewModel: FinishReportViewModel = FinishReportViewModel()
) {
    LazyColumn(
    ) {
        Log.d("DEV", "Finish Report reportState: ${viewModel.reportState.value}")
        Thread.sleep(2000)
        items(
            items = viewModel.reportState.value,
            key = {}
        ) { unfinishedReport ->
            Row() {
                Text(text = unfinishedReport.first)
                Text(text = unfinishedReport.second.toString())
            }
        }
        Log.d("DEV", "Finish Report reportState: ${viewModel.reportState.value}")

    }
}

