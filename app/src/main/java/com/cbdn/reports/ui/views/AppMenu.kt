package com.cbdn.reports.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cbdn.reports.R
import com.cbdn.reports.ui.navigation.Destinations
import com.cbdn.reports.ui.views.composables.MenuButton
import com.cbdn.reports.ui.views.composables.MenuHeader

@Composable
fun AppMenu(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxSize()
                .weight(4f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_bomberos_oficial_tools_webp),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillWidth,
            )
        }
        MenuHeader(
            textResource = R.string.submit_header,
            modifier = Modifier.weight(1f),
        )
        MenuButton(
            onClick = { navController.navigate(Destinations.SubmitNew.name) },
            label = stringResource(id = R.string.submit_new_button),
            icon = Icons.Rounded.AddCircle,
            modifier = Modifier.weight(2f)
        )
        MenuButton(
            onClick = { navController.navigate(Destinations.SubmitFinish.name) },
            label = stringResource(id = R.string.submit_finish_button),
            icon = Icons.Rounded.CheckCircle,
            modifier = Modifier.weight(2f)
        )
        MenuButton(
            onClick = { navController.navigate(Destinations.SubmitAmend.name) },
            label = stringResource(id = R.string.submit_amend_button),
            icon = Icons.Rounded.Edit,
            modifier = Modifier.weight(2f)
        )
        MenuHeader(
            textResource = R.string.view_header,
            modifier = Modifier.weight(1f),
        )
        MenuButton(
            onClick = { navController.navigate(Destinations.ViewFilter.name) },
            label = stringResource(id = R.string.view_filter_button),
            icon = ImageVector.vectorResource(id = R.drawable.summarize_24),
            modifier = Modifier.weight(2f)
        )
        MenuButton(
            onClick = { navController.navigate(Destinations.ViewStatistics.name) },
            label = stringResource(id = R.string.view_statistics_button),
            icon = ImageVector.vectorResource(id = R.drawable.pie_chart_24),
            modifier = Modifier.weight(2f)
        )
    }
}