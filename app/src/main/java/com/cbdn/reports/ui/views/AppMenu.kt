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
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
            textResource = R.string.report_submission,
            modifier = Modifier.weight(1f),
        )
        MenuButton(
            onClick = { navController.navigate(Destinations.NewReport.name) },
            labelResource = R.string.new_report,
            subLabelResource = R.string.submit_new_description,
            icon = Icons.Rounded.AddCircle,
            modifier = Modifier.weight(2f)
        )
        MenuButton(
            onClick = { navController.navigate(Destinations.FinishReport.name) },
            labelResource = R.string.finish_report,
            subLabelResource = R.string.submit_finish_description,
            icon = Icons.Rounded.CheckCircle,
            modifier = Modifier.weight(2f)
        )
        MenuHeader(
            textResource = R.string.report_database,
            modifier = Modifier.weight(1f),
        )
        MenuButton(
            onClick = { navController.navigate(Destinations.SearchReports.name) },
            labelResource = R.string.search_reports,
            subLabelResource = R.string.view_filter_description,
            icon = ImageVector.vectorResource(id = R.drawable.baseline_search_24),
            modifier = Modifier.weight(2f)
        )
//        MenuButton(
//            onClick = { navController.navigate(Destinations.ViewStatistics.name) },
//            labelResource = R.string.view_statistics,
//            subLabelResource = R.string.view_statistics_description,
//            icon = ImageVector.vectorResource(id = R.drawable.pie_chart_24),
//            modifier = Modifier.weight(2f)
//        )
    }
}