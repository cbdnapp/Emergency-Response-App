package com.cbdn.reports.ui.views.newreport
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cbdn.reports.BuildConfig
import com.cbdn.reports.R
import com.cbdn.reports.ui.viewmodel.AppViewModel
import com.cbdn.reports.ui.views.composables.FormDivider
import com.cbdn.reports.ui.views.composables.FormHeader
import com.cbdn.reports.ui.views.composables.FormMultiLineTextField
import com.cbdn.reports.ui.views.composables.FormSubHeader
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LocationDetails(
    viewModel: AppViewModel,
    modifier: Modifier
) {
    val reportState by viewModel.reportState.collectAsStateWithLifecycle()
    var pinLocation by remember { mutableStateOf(LatLng(0.0,0.0))}
    val coroutineScope = rememberCoroutineScope()
    val santoDomingo = LatLng(18.46, -69.94)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(santoDomingo, 10f)
    }
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FormHeader(textResource = R.string.submit_location_header)

        // LOCATION
        FormSubHeader(textResource = R.string.location)
        FormMultiLineTextField(
            value = reportState.location,
            updateValue = { viewModel.setLocation(it) },
            labelResource = R.string.enter_location,
            )
        FormDivider()
        GoogleMap(
            cameraPositionState = cameraPositionState,
            onMapClick = {
                coroutineScope.launch(Dispatchers.IO) {
                    viewModel.getAddress(it, BuildConfig.MAPS_API_KEY)
                }
                pinLocation = it
            }
        ) {
            if (pinLocation.latitude != 0.0 && pinLocation.longitude != 0.0) {
                Marker(
                    state = MarkerState(pinLocation),
                    draggable = false
                )
            }
        }
    }
}