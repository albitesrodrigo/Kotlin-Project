package example.com.data.model

import kotlinx.serialization.Serializable

val expenses = mutableListOf(
    Expense(id = 1,150.0, "COMESTIBLES", "Compra semanal"),
    Expense(id = 2,120.0, "FIESTA", "Fin de semana de fiesta"),
    Expense(id = 3,45.0, "SNACKS", "Tambo"),
    Expense(id = 4,20.0, "CAFES", "Starbucks"),
    Expense(id = 5,38.0, "RESTAURANTES", "Siete Sopas"),
    Expense(id = 6,15.0, "TRANSPORTE", "DiDi"),
    Expense(id = 7,25.0, "CASA", "Limpieza"),
    Expense(id = 8,35.0, "OTRO", "Servicios")
)

// var lastExpense = expenses.size.toLong()

@Serializable
data class Expense (
    val id: Long = -1,
    val amount: Double,
    val categoryName: String,
    val description: String
)