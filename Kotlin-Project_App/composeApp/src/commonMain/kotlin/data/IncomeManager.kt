package data

import model.Income
import model.IncomeCategory

object IncomeManager {

    private var currentId = 1L

    val fakeIncomeList = mutableListOf(
        Income(
            id = currentId++,
            amount = 1500.0,
            category = IncomeCategory.TRABAJO,
            description = "Servicio de FullStack"
        ),
        Income(
            id = currentId++,
            amount = 1200.0,
            category = IncomeCategory.TRABAJO,
            description = "Servicio de Android"
        ),
        Income(
            id = currentId++,
            amount = 450.0,
            category = IncomeCategory.TRABAJO,
            description = "Servicio de lámparas"
        )
    )

    fun addNewIncome(income: Income) {
        fakeIncomeList.add(income)
    }

    fun editIncome(income: Income) {
        val index = fakeIncomeList.indexOfFirst {
            it.id == income.id
        }
        if (index != -1) {
            fakeIncomeList[index] = fakeIncomeList[index].copy(
                amount = income.amount,
                category = income.category,
                description = income.description
            )
        }
    }

    fun deleteIncome(income: Income): List<Income> {
        val index = fakeIncomeList.indexOfFirst {
            it.id == income.id
        }
        if (index != -1) {
            fakeIncomeList.removeAt(index)
        }
        return fakeIncomeList
    }

    fun getCategories(): List<IncomeCategory> {
        return listOf(
            IncomeCategory.TRABAJO
        )
    }
}