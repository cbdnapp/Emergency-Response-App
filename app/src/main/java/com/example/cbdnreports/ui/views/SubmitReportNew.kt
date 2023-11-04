package com.example.cbdnreports.ui.views

import androidx.compose.runtime.Composable
import com.example.cbdnreports.ui.viewmodel.SubmitReportNewVM

@Composable
fun SubmitReportNew(
//    viewModel: SubmitReportNewVM = hiltViewModel()
) {
    // BUTTOM BAR: Button to save incomplete
        // once all fields are complete: button turns into Submit

    // Date/Time Emergency was Received
    // Date/Time of Arrived at Scene
    // Date/Time of Returning to Station

    // Emergency code Top Category (Select From List)
        // Emergency Code Sub Category (Select From List)

    // Truck (Select)
    // Commanding Officer

    // Location (Choice: enter address, drop pin, or get location?)

    // Number of Victims (Integer)
        // FOR EACH:
        // Victim Status Code (Select From List)
        // Victim Info
            // Name
            // Age
            // ID

    // Police Unit Present (Select: Yes/No)
        // if yes: Unit Number
    // Ambulance Unit Present (Select: Yes/No)
        // if yes: Unit Number
    // Electric Company Present (Select: Yes/No)
        // if yes: Unit Number
    // Transit Police Unit Present (Select: Yes/No)
        // if yes: Unit Number

    // Notes Comments
}