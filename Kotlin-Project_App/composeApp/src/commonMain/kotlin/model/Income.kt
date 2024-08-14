package model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.vector.ImageVector

data class Income (
    val id: Long = -1,
    val amount: Double,
    val category: IncomeCategory,
    val description: String
) {
    val icon = category.icon
}

enum class IncomeCategory(val icon: ImageVector) {
    TRABAJO(Icons.Default.Work)
}