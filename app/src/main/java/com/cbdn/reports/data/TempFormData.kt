package com.cbdn.reports.data
// CONTAINS TEMP DATA OBJECTS FOR GENERATING 1. EMERGENCY CODES AND 2.TRUCKS

// 1. EMERGENCY CODES
data class EmergencyCodes(
    val code: String,
    val name: String
)

data class EmergencyCategories(
    val id: String,
    val category: String,
)

object EmergencyCodeData {

    fun getCategories(): List<EmergencyCategories> {
        return listOf(
            EmergencyCategories(
                id = "F",
                category = "Incendio"
            ),
            EmergencyCategories(
                id = "R",
                category = "Rescate"
            ),
            EmergencyCategories(
                id = "I",
                category = "Accidente"
            ),
            EmergencyCategories(
                id = "O",
                category = "Otro"
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
                    name = "Incendio Residencial (F-1)",
                ),
                EmergencyCodes(
                    code = "F-2",
                    name = "Incendio Comercial (F-2)",
                ),
                EmergencyCodes(
                    code = "F-3",
                    name = "Escape de GLP (F-3)",
                ),
                EmergencyCodes(
                    code = "F-4",
                    name = "Incendio Eléctrico (F-4)",
                ),
                EmergencyCodes(
                    code = "F-5",
                    name = "Incendio de Vehículo de Motor (F-5)",
                ),
                EmergencyCodes(
                    code = "F-6",
                    name = "Incendio Forestal o Vertedero (F-6)",
                ),
                EmergencyCodes(
                    code = "F-7",
                    name = "Materiales Peligrosos (F-7)",
                ),
                EmergencyCodes(
                    code = "F-8",
                    name = "Derrame de Combustible (F-8)",
                ),
                EmergencyCodes(
                    code = "F-9",
                    name = "Incendio de una Embarcación (F-9)",
                ),
                EmergencyCodes(
                    code = "F-10",
                    name = "Incendio de Aeronave (F-10)",
                ),
            )
            "R" -> returnList = listOf(
                EmergencyCodes(
                    code = "R-1",
                    name = "Rescate Acuático (R-1)",
                ),
                EmergencyCodes(
                    code = "R-2",
                    name = "Rescate Terrestre (R-2)",
                ),
                EmergencyCodes(
                    code = "R-3",
                    name = "Rescate en Altura (R-3)",
                ),
                EmergencyCodes(
                    code = "R-4",
                    name = "Rescate de Animales (R-4)",
                ),
                EmergencyCodes(
                    code = "R-5",
                    name = "Rescate Vehícular (R-5)",
                ),
                EmergencyCodes(
                    code = "R-6",
                    name = "Intento de Suicidio (R-6)",
                ),
            )
            "I" -> returnList = listOf(
                EmergencyCodes(
                    code = "I-15",
                    name = "Accidente Personal (I-15)",
                ),
                EmergencyCodes(
                    code = "I-16",
                    name = "Accidente de Tránsito (I-16)",
                ),
                EmergencyCodes(
                    code = "I-17",
                    name = "Accidente Aereo (I-17)",
                ),
            )
            "O" -> returnList = listOf(
                EmergencyCodes(
                    code = "E-1",
                    name = "Explosión (E-1)",
                ),
                EmergencyCodes(
                    code = "D-2",
                    name = "Derrumbe o Deslizamiento de Tierra (D-2)",
                ),
                EmergencyCodes(
                    code = "I-1",
                    name = "Inundación (I-1)",
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

// 2. EMERGENCY CODES
data class Trucks(
    val code: String
)

object TruckData {
    val default = null

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