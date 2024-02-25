package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.cbdn.reports.R
import com.cbdn.reports.data.TruckData
import com.cbdn.reports.data.Trucks
import com.cbdn.reports.ui.navigation.Destinations
import com.cbdn.reports.ui.viewmodel.AppViewModel
import com.cbdn.reports.ui.views.composables.DialogHeader

@Composable
fun SelectTruckDialog(
    appViewModel: AppViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val truckDetails: List<Trucks> = TruckData.getTrucks()
    val reportState by appViewModel.reportState.collectAsStateWithLifecycle()

    Dialog(
        onDismissRequest = {
            appViewModel.setRespondingTruck("")
            appViewModel.setIsTruckSelectShowing(false)
                           },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ){
            LazyVerticalGrid(
                columns=GridCells.Fixed(5),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ){
                item(
                    span = { GridItemSpan(5)}
                ) {
                    Text(
                        text = stringResource(R.string.select_truck_dialog),
                        modifier = Modifier
                            .padding(
                                top = dimensionResource(id = R.dimen.moderate_spacing),
                                bottom = 4.dp
                            ),
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Center
                    )
                }

                items(truckDetails) {truckDetail ->
                    TruckCard(
                        truck = truckDetail,
                        curSelectedTruck = reportState.respondingTruck,
                        setRespondingTruck = { appViewModel.setRespondingTruck(truckDetail.code) },
                        modifier = modifier
                        )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
                ) {
                FormButton(
                    onClick = {
                        appViewModel.setRespondingTruck("")
                        appViewModel.setIsTruckSelectShowing(false)
                    },
                    labelResource = R.string.cancel,
                    modifier = Modifier.height(80.dp)
                )
                FormButton(
                    onClick = {
                        if (reportState.respondingTruck != null){
                            appViewModel.setIsTruckSelectShowing(false)
                            navController.navigate(Destinations.AppMenu.name)
                        }
                    },
                    labelResource = R.string.submit,
                    modifier = Modifier.height(80.dp)
                )
            }
        }
    }
}

@Composable
fun TruckCard(
    truck: Trucks,
    curSelectedTruck: String?,
    setRespondingTruck: (String)->Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = {
            setRespondingTruck(truck.code)
        },
        border = BorderStroke(2.dp, Color(0xFFAAAAAA)),
        modifier = modifier.width(80.dp).height(60.dp)

    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .then(
                    if (curSelectedTruck==truck.code) Modifier.background(color=Color(0xFFAAAAAA)) else Modifier
                )
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = truck.code,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
        }
    }
}