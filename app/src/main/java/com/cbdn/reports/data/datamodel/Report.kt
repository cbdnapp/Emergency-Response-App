package com.cbdn.reports.data.datamodel

data class Report(
    // dispatch
    var respondingTruck: String? = null,
    var commandingOfficer: String? = null,
    var datetimeDispatch: Long? = null,
    var emergencyCode: String? = null,
    // location
    var location: String? = null,
    // on scene
    var datetimeArrival: Long? = null,
    var policePresent: String? = null,
    var ambulancePresent: String? = null,
    var electricCompanyPresent: String? = null,
    var transitPolicePresent: String? = null,
    var victimInfo: List<VictimInfo> = emptyList(),
    var notes: String? = null,
    // submittal
    var datetimeReturn: Long? = null,
    var reportWriter: String? = null,
)