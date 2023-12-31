package com.cbdn.reports.data

data class EmergencyCategories(
    val id: String,
    val category: String,
)

data class EmergencyCodes(
    val code: String,
    val name: String
)

data class Trucks(
    val code: String
)

data class VictimCodes(
    val code: String,
    val name: String
)

object EmergencyCodeData {

    fun getCategories(): List<EmergencyCategories> {
        return listOf(
            EmergencyCategories(
                id = "F",
                category = "Fire"
            ),
            EmergencyCategories(
                id = "R",
                category = "Rescue"
            ),
            EmergencyCategories(
                id = "I",
                category = "Accident"
            ),
            EmergencyCategories(
                id = "O",
                category = "Other"
            ),
        )
    }

    fun getCode(
        emergencyCode: EmergencyCategories): List<EmergencyCodes> {
        var returnList: List<EmergencyCodes> = emptyList()
        when (emergencyCode.id) {
            "F" -> returnList = listOf(
                EmergencyCodes(
                    code = "F-1",
                    name = "Residential Fire (F-1)",
                ),
                EmergencyCodes(
                    code = "F-2",
                    name = "Commercial Fire (F-2)",
                ),
                EmergencyCodes(
                    code = "F-3",
                    name = "LPG exhaust (F-3)",
                ),
                EmergencyCodes(
                    code = "F-4",
                    name = "Electrical Fire (F-4)",
                ),
                EmergencyCodes(
                    code = "F-5",
                    name = "Motor Vehicle Fire (F-5)",
                ),
                EmergencyCodes(
                    code = "F-6",
                    name = "Forest Fire or Landfill (F-6)",
                ),
                EmergencyCodes(
                    code = "F-7",
                    name = "Dangerous materials (F-7)",
                ),
                EmergencyCodes(
                    code = "F-8",
                    name = "Fuel spill (F-8)",
                ),
                EmergencyCodes(
                    code = "F-9",
                    name = "Boat Fire (F-9)",
                ),
                EmergencyCodes(
                    code = "F-10",
                    name = "Aircraft Fire (F-10)",
                ),
            )
            "R" -> returnList = listOf(
                EmergencyCodes(
                    code = "R-1",
                    name = "Aquatic rescue (R-1)",
                ),
                EmergencyCodes(
                    code = "R-2",
                    name = "Land Rescue (R-2)",
                ),
                EmergencyCodes(
                    code = "R-3",
                    name = "Rescue at Height (R-3)",
                ),
                EmergencyCodes(
                    code = "R-4",
                    name = "Animal Rescue (R-4)",
                ),
                EmergencyCodes(
                    code = "R-5",
                    name = "Vehicle Rescue (R-5)",
                ),
                EmergencyCodes(
                    code = "R-6",
                    name = "Suicide attempt (R-6)",
                ),
            )
            "I" -> returnList = listOf(
                EmergencyCodes(
                    code = "I-15",
                    name = "Personal Accident (I-15)",
                ),
                EmergencyCodes(
                    code = "I-16",
                    name = "Car accident (I-16)",
                ),
                EmergencyCodes(
                    code = "I-17",
                    name = "Air crash (I-17)",
                ),
            )
            "O" -> returnList = listOf(
                EmergencyCodes(
                    code = "E-1",
                    name = "Burst (E-1)",
                ),
                EmergencyCodes(
                    code = "D-2",
                    name = "Landslide (D-2)",
                ),
                EmergencyCodes(
                    code = "I-1",
                    name = "Flood (I-1)",
                ),
                EmergencyCodes(
                    code = "PODA",
                    name = "PODA",
                ),
            )
        }
        return returnList
    }
}

object TruckData {

    fun getTrucks(): List<Trucks> {
        return listOf(
            Trucks(code = "R-30"),
            Trucks(code = "R-32"),
            Trucks(code = "R-36"),
            Trucks(code = "B-2"),
            Trucks(code = "B-3"),
            Trucks(code = "B-4"),
            Trucks(code = "B-5"),
            Trucks(code = "B-6"),
            Trucks(code = "B-7"),
            Trucks(code = "B-9"),
            Trucks(code = "B-10"),
            Trucks(code = "B-11"),
            Trucks(code = "B-12"),
            Trucks(code = "B-16"),
            Trucks(code = "B-15"),
            Trucks(code = "B-18"),
            Trucks(code = "B-19"),
            Trucks(code = "B-20"),
            Trucks(code = "B-21"),
            Trucks(code = "B-22"),
            Trucks(code = "B-31"),
            Trucks(code = "C-9"),
            Trucks(code = "C-10"),
            Trucks(code = "C-12"),
            Trucks(code = "C-21"),
            Trucks(code = "C-22"),
            Trucks(code = "C-23"),
            Trucks(code = "C-24"),
            Trucks(code = "E-26"),
            Trucks(code = "E-27"),
            Trucks(code = "E-28"),
        )
    }
}

object VictimCodeData {
    fun getCode(): List<VictimCodes> {
        return listOf(
            VictimCodes(
                code = "I-26",
                name = "Person without physical damage (I-26)",
            ),
            VictimCodes(
                code = "I-27",
                name = "Slightly affected person (I-27)",
            ),
            VictimCodes(
                code = "I-28",
                name = "Person with serious injuries (I-28)",
            ),
            VictimCodes(
                code = "I-28 ADVANCED",
                name = "Person in critical condition (I-28 ADVANCED)",
            ),
            VictimCodes(
                code = "I-29",
                name = "Deceased person (I-29)",
            ),
        )
    }
}