package com.cbdn.reports.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.cbdn.reports.data.datamodel.FireStoreUtility
import com.cbdn.reports.data.datamodel.Report
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AppUiState(
    val submitClicked: Boolean = false,
    val prevScreen: String? = null,
    val report: Report? = null,
    val reportID: String? = null
)
class AppViewModel(
    private val db: FireStoreUtility = FireStoreUtility()
) : ViewModel() {
    private var _uiState = MutableStateFlow(AppUiState())
    var uiState: StateFlow<AppUiState> = _uiState.asStateFlow()
    init {
        Log.d("DEV", "AppViewModel init.")
    }
    override fun onCleared() {
        super.onCleared()
        Log.d("DEV", "AppViewModel onCleared.")
    }
    fun resetUiState() {
        _uiState.update {
            it.copy(
                submitClicked = false,
                prevScreen = null,
                report = null,
                reportID = null
                )
        }
    }
    fun setLastScreen(input: String?) {
        _uiState.update {
            it.copy(prevScreen = input)
        }
    }
    fun setReport(input: Report) {
        _uiState.update {
            it.copy(report = input)
        }
    }
    fun setSubmitButtonClicked(input: Boolean) {
        _uiState.update {
            it.copy(submitClicked = input)
        }
    }
    fun submitReport() {
        if (uiState.value.reportID != null) {
            uiState.value.report?.let { db.updateReport(it, uiState.value.reportID!!) }
        } else if (uiState.value.report != null){
            db.submitReport(uiState.value.report!!)
        }
    }
}