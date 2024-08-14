package data

import com.ibitApp.db.AppDatabase
import domain.ExpenseRepository
import model.Expense
import model.ExpenseCategory

class ExpenseRepoImpl(
    private val appDatabase: AppDatabase
) : ExpenseRepository {

    private val expensesDbQueries = appDatabase.expensesDbQueries

    override fun getAllExpenses(): List<Expense> {
        return expensesDbQueries.selectAll().executeAsList().map {
            Expense(
                id = it.id,
                amount = it.amount,
                category = ExpenseCategory.valueOf(it.categoryName),
                description = it.descriprion
            )
        }
    }

    override fun addExpense(expense: Expense) {
        expensesDbQueries.transaction {
            expensesDbQueries.insert(
                amount = expense.amount,
                categoryName = expense.category.name,
                descriprion = expense.description
            )
        }
    }

    override fun editExpense(expense: Expense) {
        expensesDbQueries.transaction {
            expensesDbQueries.update(
                id = expense.id,
                amount = expense.amount,
                categoryName = expense.category.name,
                descriprion = expense.description
            )
        }    }

    override fun deleteExpense(expense: Expense): List<Expense> {
        TODO("Not yet implemented")
    }

    override fun getCategories(): List<ExpenseCategory> {
        return expensesDbQueries.categories().executeAsList().map {
            ExpenseCategory.valueOf(it)
        }
    }
}