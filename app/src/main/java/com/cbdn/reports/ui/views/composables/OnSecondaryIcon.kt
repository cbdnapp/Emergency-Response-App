import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

@Composable
fun OnSecondaryIcon(
    iconResource: Int,
) {
    Icon(
        imageVector = ImageVector.vectorResource(
            id = iconResource),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onSecondary
    )
}