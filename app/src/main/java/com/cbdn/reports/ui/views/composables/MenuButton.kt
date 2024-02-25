package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cbdn.reports.R

@Composable
fun MenuButton(
    onClick: () -> Unit,
    labelResource: Int,
    subLabelResource: Int,
    icon: ImageVector,
    modifier: Modifier,
    isRound: Boolean = false
) {
    OutlinedCard(
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary),
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        modifier = modifier
            .clickable(onClick = onClick)

            .padding(dimensionResource(id = R.dimen.thin_spacing))
            .then(
                if (isRound) Modifier.clip(RoundedCornerShape(50.dp)) else Modifier
            )
//            .clip(RoundedCornerShape(50.dp))
            .fillMaxSize(),
    ) {
        Row(
            modifier = modifier

                .fillMaxSize(),
        ) {
            Box(modifier = Modifier.weight(.3f)) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.thin_spacing)),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier
                            .scale(1.5f),
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
            Box(modifier = Modifier.weight(.7f)) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(
                            start = dimensionResource(id = R.dimen.thin_spacing),
                            end = dimensionResource(id = R.dimen.moderate_spacing)
                        ),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = labelResource),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = stringResource(id = subLabelResource),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}