package domain

import model.Expense
import model.ExpenseCategory

interface ExpenseRepository {
    fun getAllExpenses(): List<Expense>
    fun addExpense(expense: Expense)
    fun editExpense(expense: Expense)
    fun deleteExpense(expense: Expense): List<Expense>
    fun getCategories(): List<ExpenseCategory>
}