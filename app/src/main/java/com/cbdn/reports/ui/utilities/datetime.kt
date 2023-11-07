import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun convertMillisToDate(millis: Long?): String? {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    if (millis != null) return formatter.format(Date(millis))
    return null
}