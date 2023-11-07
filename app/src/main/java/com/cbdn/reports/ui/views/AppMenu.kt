package com.cbdn.reports.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cbdn.reports.R
import com.cbdn.reports.ui.navigation.Destinations
import com.cbdn.reports.ui.theme.CBDNReportsTheme

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
        Spacer(modifier = Modifier.weight(.1f))
        Box(
            modifier = Modifier
                .paint(painterResource(id = R.drawable.logo_bomberos_oficial_png_peq))
                .weight(1.5f)
        )
        Box(
            modifier = Modifier
            .weight(3f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(.1f))
                MenuHeader(label = stringResource(id = R.string.submit_report_header))
                MenuButton(
                    onClick = { navController.navigate(Destinations.SubmitReportNew.name) },
                    label = stringResource(id = R.string.submit_report_new_button)
                )
                MenuButton(
                    onClick = { navController.navigate(Destinations.SubmitReportFinish.name) },
                    label = stringResource(id = R.string.submit_report_finish_button)
                )
                MenuButton(
                    onClick = { navController.navigate(Destinations.SubmitReportAmend.name) },
                    label = stringResource(id = R.string.submit_report_amend_button)
                )
                Spacer(modifier = Modifier.weight(.1f))
                MenuHeader(label = stringResource(id = R.string.view_report_header))
                MenuButton(
                    onClick = { navController.navigate(Destinations.ViewReportFilter.name) },
                    label = stringResource(id = R.string.view_report_filter_button)
                )
                MenuButton(
                    onClick = { navController.navigate(Destinations.ViewReportStatistics.name) },
                    label = stringResource(id = R.string.view_report_statistics_button)
                )
            }
        }
    }
}

@Composable
fun MenuHeader(
    label: String
) {
    Text(
        text = label,
        fontSize = 21.sp
    )
}

@Composable
fun MenuButton(
    onClick: () -> Unit,
    label: String
) {
    Button(
        onClick = onClick,
        modifier = Modifier.width(150.dp)
    ) {
        Text(
            text = label,
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPreview() {
    CBDNReportsTheme {
//        Menu()
    }
}