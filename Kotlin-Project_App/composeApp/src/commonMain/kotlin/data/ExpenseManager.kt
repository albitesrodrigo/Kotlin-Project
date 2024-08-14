package data

import model.Expense
import model.ExpenseCategory

object ExpenseManager {

    private var currentId = 1L

    val fakeExpenseList = mutableListOf(
        Expense(
            id = currentId++,
            amount = 150.0,
            category = ExpenseCategory.COMESTIBLES,
            description = "Compra semanal"
        ),
        Expense(
            id = currentId++,
            amount = 120.0,
            category = ExpenseCategory.FIESTA,
            description = "Fin de semana de fiesta"
        ),
        Expense(
            id = currentId++,
            amount = 45.0,
            category = ExpenseCategory.SNACKS,
            description = "Tambo"
        ),
        Expense(
            id = currentId++,
            amount = 20.0,
            category = ExpenseCategory.CAFES,
            description = "Starbucks"
        ),
        Expense(
            id = currentId++,
            amount = 38.0,
            category = ExpenseCategory.RESTAURANTES,
            description = "Siete Sopas"
        ),
        Expense(
            id = currentId++,
            amount = 15.0,
            category = ExpenseCategory.TRANSPORTE,
            description = "DiDi"
        ),
        Expense(
            id = currentId++,
            amount = 25.0,
            category = ExpenseCategory.CASA,
            description = "Limpieza"
        ),
        Expense(
            id = currentId++,
            amount = 35.0,
            category = ExpenseCategory.OTRO,
            description = "Servicios"
        )
    )

    fun addNewExpense(expense: Expense) {
        fakeExpenseList.add(expense.copy(id = currentId++))
    }

    fun editExpense(expense: Expense) {
        val index = fakeExpenseList.indexOfFirst {
            it.id == expense.id
        }
        if (index != -1) {
            fakeExpenseList[index] = fakeExpenseList[index].copy(
                amount = expense.amount,
                category = expense.category,
                description = expense.description
            )
        }
    }

    fun deleteExpense(expense: Expense): List<Expense> {
        val index = fakeExpenseList.indexOfFirst {
            it.id == expense.id
        }
        if (index != -1) {
            fakeExpenseList.removeAt(index)
        }
        return fakeExpenseList
    }

    fun getCategories(): List<ExpenseCategory> {
        return listOf(
            ExpenseCategory.COMESTIBLES,
            ExpenseCategory.FIESTA,
            ExpenseCategory.SNACKS,
            ExpenseCategory.CAFES,
            ExpenseCategory.RESTAURANTES,
            ExpenseCategory.TRANSPORTE,
            ExpenseCategory.CASA,
            ExpenseCategory.OTRO
        )
    }
}