package com.cbdn.reports.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cbdn.reports.data.datamodel.FireStoreUtility
import com.cbdn.reports.data.datamodel.Report
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FinishReportViewModel: ViewModel() {
    private var _unfinishedReports = MutableStateFlow(emptyList<Pair<String, Report>>())
    var reportState: StateFlow<List<Pair<String, Report>>> = _unfinishedReports.asStateFlow()

    init {
        getUnfinishedReports()
    }
    private fun getUnfinishedReports() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val reports: List<Pair<String, Report>> = FireStoreUtility().getReport(finalized = false)
            _unfinishedReports.update {
                it + reports
            }
        }
        Log.d("DEV", "ViewModel reportState: ${reportState.value}")
    }
}