package com.cbdn.reports

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.cbdn.reports.ui.theme.CBDNReportsTheme
import com.cbdn.reports.ui.viewmodel.AppViewModel
import com.cbdn.reports.ui.views.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CBDNReportsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    App(appViewModel = AppViewModel())
                }
            }
        }

    }
}