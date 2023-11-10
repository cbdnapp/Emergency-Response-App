package com.cbdn.reports.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.cbdn.reports.ui.navigation.Destinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class VictimInfo(
    var statusCode: String?,
    var name: String?,
    var age: Int?,
    var identification: String?,
)

data class SubmitNewUiState(
    // Report data matches Data Model for upload
    // dispatch
    var respondingTruck: String? = null,
    var commandingOfficer: String? = null,
    var datetimeDispatch: Long? = null,
    var emergencyCode: String? = null,
    // location
    var location: String? = null,
    // on scene
    var datetimeArrival: Long? = null,
    var policePresent: String? = null,
    var ambulancePresent: String? = null,
    var electricCompanyPresent: String? = null,
    var transitPolicePresent: String? = null,
    var victimCount: Int? = null,
    var victimInfo: List<VictimInfo> = emptyList(),
    var notes: String? = null,
    // submittal
    var datetimeReturn: Long? = null,
    var author: String? = null,

    // VARIABLES FOR UI FORM
    var currentScreen: String? = Destinations.SubmitNewDispatch.name,
    var dispatchComplete: Boolean = false,
    var locationComplete: Boolean = false,
    var onSceneComplete: Boolean = false,
    var submitComplete: Boolean = false,
    var reportComplete: Boolean = false,
    // dispatch
    var categoryIndex: Int = 0,
    var selectedOptionText: String? = null,
    // location
    // on scene
    var policeCheck: Boolean = false,
    var ambulanceCheck: Boolean = false,
    var electricCompanyCheck: Boolean = false,
    var transitPoliceCheck: Boolean = false,
    // submittal
)

class SubmitNewViewModel : ViewModel() {

    private var _uiState = MutableStateFlow(SubmitNewUiState())
    var uiState: StateFlow<SubmitNewUiState> = _uiState.asStateFlow()

    fun setCurrentScreen(screen: String?) {
        _uiState.update {
            _uiState.value.copy(currentScreen = screen)
        }
    }
    private fun isReportComplete() {
        if (
            this.uiState.value.dispatchComplete &&
            this.uiState.value.locationComplete &&
            this.uiState.value.onSceneComplete &&
            this.uiState.value.submitComplete
            ) {
            _uiState.update {
                _uiState.value.copy(reportComplete = true)
            }
        } else {
            _uiState.update {
                _uiState.value.copy(reportComplete = false)
            }
        }
    }
    private fun isDispatchComplete() {
        if (
            this.uiState.value.respondingTruck != null &&
            this.uiState.value.commandingOfficer != null &&
            this.uiState.value.datetimeDispatch != null &&
            this.uiState.value.emergencyCode != null
        ) {
            _uiState.update {
                _uiState.value.copy(dispatchComplete = true)
            }
        } else {
            _uiState.update {
                _uiState.value.copy(dispatchComplete = false)
            }
        }
        isReportComplete()
    }
    private fun isLocationComplete() {
        if (
            this.uiState.value.location != null
        ) {
            _uiState.update {
                _uiState.value.copy(locationComplete = true)
            }
        } else {
            _uiState.update {
                _uiState.value.copy(locationComplete = false)
            }
        }
        isReportComplete()
    }
    private fun isOnSceneComplete() {
        if (
            this.uiState.value.datetimeArrival != null &&
            this.uiState.value.policePresent != null &&
            this.uiState.value.ambulancePresent != null &&
            this.uiState.value.electricCompanyPresent != null &&
            this.uiState.value.transitPolicePresent != null &&
            this.uiState.value.victimCount != null &&
            this.uiState.value.victimInfo.size == this.uiState.value.victimCount &&
            this.uiState.value.notes != null
        ) {
            _uiState.update {
                _uiState.value.copy(onSceneComplete = true)
            }
        } else {
            _uiState.update {
                _uiState.value.copy(onSceneComplete = false)
            }
        }
        isReportComplete()
    }
    private fun isSubmitComplete() {
        if (
            this.uiState.value.datetimeReturn != null &&
            this.uiState.value.author != null
        ) {
            _uiState.update {
                _uiState.value.copy(submitComplete = true)
            }
            isReportComplete()
        }
    }

    fun setRespondingTruck(input: String) {
        _uiState.update {
            _uiState.value.copy(respondingTruck = input.ifEmpty { null })
        }
        isDispatchComplete()
    }

    fun setCommandingOfficer(input: String) {
        _uiState.update {
            _uiState.value.copy(commandingOfficer = input.ifEmpty { null })
        }
        isDispatchComplete()
    }

    fun setDatetimeDispatch(input: Long) {
        _uiState.update {
            _uiState.value.copy(datetimeDispatch = input)
        }
        isDispatchComplete()
    }
    fun setCategoryIndex(input: Int) {
        _uiState.update {
            _uiState.value.copy(categoryIndex = input)
        }
    }
    fun setSelectedOptionText(input: String) {
        _uiState.update {
            _uiState.value.copy(selectedOptionText = input)
        }
    }
    fun setEmergencyCode(input: String) {
        _uiState.update {
            _uiState.value.copy(emergencyCode = input.ifEmpty { null })
        }
        isDispatchComplete()
    }
    fun setLocation(input: String) {
        _uiState.update {
            _uiState.value.copy(location = input.ifEmpty { null })
        }
        isLocationComplete()
    }
    fun setDatetimeArrival(input: Long) {
        _uiState.update {
            _uiState.value.copy(datetimeArrival = input)
        }
        isOnSceneComplete()
    }
    fun setPoliceCheck(input: Boolean) {
        _uiState.update {
            _uiState.value.copy(policeCheck = input)
        }
        if (input) setPolicePresent(null)
        else setPolicePresent("")
    }
    fun setPolicePresent(input: String?) {
        _uiState.update {
            _uiState.value.copy(policePresent = input)
        }
        isOnSceneComplete()
    }
    fun setAmbulanceCheck(input: Boolean) {
        _uiState.update {
            _uiState.value.copy(ambulanceCheck = input)
        }
        if (input) setAmbulancePresent(null)
        else setAmbulancePresent("")
    }
    fun setAmbulancePresent(input: String?) {
        _uiState.update {
            _uiState.value.copy(ambulancePresent = input)
        }
        isOnSceneComplete()
    }
    fun setElectricCompanyCheck(input: Boolean) {
        _uiState.update {
            _uiState.value.copy(electricCompanyCheck = input)
        }
        if (input) setElectricCompanyPresent(null)
        else setElectricCompanyPresent("")
    }
    fun setElectricCompanyPresent(input: String?) {
        _uiState.update {
            _uiState.value.copy(electricCompanyPresent = input)
        }
        isOnSceneComplete()
    }
    fun setTransitPoliceCheck(input: Boolean) {
        _uiState.update {
            _uiState.value.copy(transitPoliceCheck = input)
        }
        if (input) setTransitPolicePresent(null)
        else setTransitPolicePresent("")
    }
    fun setTransitPolicePresent(input: String?) {
        _uiState.update {
            _uiState.value.copy(transitPolicePresent = input)
        }
        isOnSceneComplete()
    }
    fun setVictimCount(input: Int?) {
        _uiState.update {
            _uiState.value.copy(victimCount = input)
        }
        isOnSceneComplete()
    }
    fun setNotes(input: String) {
        _uiState.update {
            _uiState.value.copy(notes = input.ifEmpty { null })
        }
        isOnSceneComplete()
    }
    fun setDatetimeReturn(input: Long) {
        _uiState.update {
            _uiState.value.copy(datetimeReturn = input)
        }
        isSubmitComplete()
    }
    fun setAuthor(input: String) {
        _uiState.update {
            _uiState.value.copy(author = input.ifEmpty { null })
        }
        isSubmitComplete()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("Developer", "onCleared")
    }
}