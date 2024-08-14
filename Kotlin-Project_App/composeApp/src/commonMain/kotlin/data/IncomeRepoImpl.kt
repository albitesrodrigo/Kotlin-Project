package data

import domain.IncomeRepository
import model.Income
import model.IncomeCategory

class IncomeRepoImpl(private val incomeManager: IncomeManager) : IncomeRepository {
    override fun getAllIncomes(): List<Income> {
        return incomeManager.fakeIncomeList
    }

    override fun addIncome(income: Income) {
        incomeManager.addNewIncome(income)
    }

    override fun editIncome(income: Income) {
        incomeManager.editIncome(income)
    }

    override fun deleteIncome(income: Income): List<Income> {
        return incomeManager.deleteIncome(income)
    }

    override fun getCategories(): List<IncomeCategory> {
        return incomeManager.getCategories()
    }
}