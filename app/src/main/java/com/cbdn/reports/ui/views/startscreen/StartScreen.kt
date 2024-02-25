package com.cbdn.reports.ui.views.startscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle

import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cbdn.reports.R
import com.cbdn.reports.ui.navigation.Destinations
import com.cbdn.reports.ui.viewmodel.AppViewModel
import com.cbdn.reports.ui.views.composables.MenuButton
import com.cbdn.reports.ui.views.composables.SelectTruckDialog

@Composable
fun StartScreen(
    appViewModel: AppViewModel,
    navController: NavController
){
    val uiState by appViewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondary),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Card(
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxSize()
                .weight(3f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_bomberos_oficial_tools_webp),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillWidth,
            )
        }
        Spacer(
            modifier = Modifier
                .height(12.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondary)
        )
        MenuButton(
            // this should open up the menu to choose which truck you're on
            // and then once that's submitted, move to the landing page

//            onClick = { navController.navigate(Destinations.AppMenu.name) },
            onClick = {appViewModel.setIsTruckSelectShowing(true)},
            labelResource = R.string.select_truck,
            subLabelResource = R.string.select_truck_description,
            icon =  ImageVector.vectorResource(R.drawable.fire_truck),
            modifier = Modifier.weight(2f),
            isRound = true
        )
        Spacer(
            modifier = Modifier
                .height(136.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondary)
        )
        MenuButton(
            // Moves to "App Menu" to tie this tested UI with the reporting function
            // this should be tied to the Admin Login Screen instead
            onClick = { navController.navigate(Destinations.AppMenu.name) },

            labelResource = R.string.admin_login,
            subLabelResource = R.string.admin_login_description,
            icon = ImageVector.vectorResource(R.drawable.admin_login),
            modifier = Modifier.weight(1f),
            isRound = true
        )
        Spacer(
            modifier = Modifier
                .height(36.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondary)
        )

    }
    if (uiState.isTruckSelectShowing) {
        SelectTruckDialog(
            appViewModel = appViewModel,
            navController = navController,
            modifier = Modifier
        )
    }
}

