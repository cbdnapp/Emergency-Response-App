package com.cbdn.reports.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class VictimInfo(
    var statusCode: String? = null,
    var name: String? = null,
    var age: Int? = null,
    var identification: String? = null,
)

data class Report(
    // dispatch details
    var respondingTruck: String? = null,
    var commandingOfficer: String? = null,
    var datetimeDispatch: Long? = null,
    var emergencyCode: String? = null,
    var location: String? = null,

    // on scene details
    var datetimeArrival: Long? = null,
    var policePresent: String? = null,
    var ambulancePresent: String? = null,
    var electricCompanyPresent: String? = null,
    var transitPolicePresent: String? = null,
    var victimCount: Int? = null,
    var victimInfo: List<VictimInfo> = emptyList(),
    var notes: String? = null,

    // submittal details
    var datetimeReturn: Long? = null,
    var author: String? = null,
)
data class SubmitNewReportUiState(
    var report: Report = Report()
)

class SubmitReportNewViewModel : ViewModel() {

    // Expose screen UI state
    private var _uiState = MutableStateFlow(SubmitNewReportUiState())
    var uiState: StateFlow<SubmitNewReportUiState> = _uiState.asStateFlow()

    init {
        Log.d("Developer", "initialized")
    }

    // Handle business logic
    override fun onCleared() {
        super.onCleared()
        Log.d("Developer", "onCleared")
    }
}