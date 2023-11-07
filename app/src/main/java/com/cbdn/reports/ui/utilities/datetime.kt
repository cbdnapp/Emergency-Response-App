import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun convertMillisToDateTime(millis: Long?): String? {
    val formatter = SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.US)
    if (millis != null) return formatter.format(Date(millis))
    return null
}