package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.cbdn.reports.R

@Composable
fun VictimInfoCard(
    index: Int,
    statusCode: String,
    name: String,
    age: String,
    identification: String,
    remove: () -> Unit,
    edit: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.full_field_width)),
        shape = RectangleShape
        ) {
            Column(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.moderate_spacing))
            ) {
                Text(
                    text = stringResource(
                        id = R.string.victim_number_with_data,
                        (index + 1).toString()
                    )
                )
                Text(text = stringResource(id = R.string.victim_status_code_with_data, statusCode))
                Text(text = stringResource(id = R.string.victim_name_with_data, name))
                Text(text = stringResource(id = R.string.victim_age_with_data, age))
                Text(
                    text = stringResource(
                        id = R.string.victim_identification_with_data,
                        identification
                    )
                )
                Row() {
                    FormButton(
                        onClick = edit,
                        labelResource = R.string.edit,
                        modifier = Modifier.weight(1f)

                    )
                    FormButton(
                        onClick = remove,
                        labelResource = R.string.remove,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
}