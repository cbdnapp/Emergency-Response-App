package com.cbdn.reports.ui.views.newreport

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cbdn.reports.R
import com.cbdn.reports.ui.viewmodel.AppViewModel
import com.cbdn.reports.ui.views.composables.BasicTextField
import com.cbdn.reports.ui.views.composables.FormDivider
import com.cbdn.reports.ui.views.composables.FormHeader
import com.cbdn.reports.ui.views.composables.FormSubHeader
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun LocationDetails(
    viewModel: AppViewModel,
    modifier: Modifier
) {
    val reportState by viewModel.reportState.collectAsStateWithLifecycle()
//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FormHeader(textResource = R.string.submit_location_header)

        // LOCATION
        FormSubHeader(textResource = R.string.location)
        BasicTextField(
            value = reportState.location,
            updateValue = { viewModel.setLocation(it) },
            labelResource = R.string.enter_location
        )
        FormDivider()
        val singapore = LatLng(1.35, 103.87)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(singapore, 10f)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        )
    }
}