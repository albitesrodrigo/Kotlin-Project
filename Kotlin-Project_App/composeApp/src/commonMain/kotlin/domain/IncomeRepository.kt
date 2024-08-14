package domain

import model.Income
import model.IncomeCategory

interface IncomeRepository {
    fun getAllIncomes(): List<Income>
    fun addIncome(income: Income)
    fun editIncome(income: Income)
    fun deleteIncome(income: Income): List<Income>
    fun getCategories(): List<IncomeCategory>
}