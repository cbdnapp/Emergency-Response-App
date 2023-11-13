package com.example.post_poc

data class Report(
    val ambulancePresent: List<String>?,
    val author: List<Any>?,
    val commandingOfficer: List<String>?,
    val datetimeArrival: List<String>?,
    val datetimeDispatch: List<String>?,
    val datetimeReturn: List<String>?,
    val electricCompanyPresent: List<Any>?,
    val emergencyCode: List<String>?,
    val finalized: Boolean,
    val location: List<String>?,
    val notes: List<String>?,
    val policePresent: List<String>?,
    val respondingTruck: List<String>?,
    val transitPolicePresent: List<Any>?,
    val victimCount: List<Int>?,
    val victimInfo: List<VictimInfo>?
)

data class ReportResponse(val id: Int?)