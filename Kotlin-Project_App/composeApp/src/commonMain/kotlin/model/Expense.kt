package model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.PartyMode
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ViewCozy
import androidx.compose.ui.graphics.vector.ImageVector

data class Expense(
    val id: Long = -1,
    val amount: Double,
    val category: ExpenseCategory,
    val description: String
) {
    val icon = category.icon
}

enum class ExpenseCategory(val icon: ImageVector) {
    COMESTIBLES(Icons.Default.FoodBank),
    FIESTA(Icons.Default.PartyMode),
    SNACKS(Icons.Default.Fastfood),
    CAFES(Icons.Default.Coffee),
    RESTAURANTES(Icons.Default.Restaurant),
    TRANSPORTE(Icons.Default.ElectricCar),
    CASA(Icons.Default.House),
    OTRO(Icons.Default.ViewCozy)
}