package presentation

import domain.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.Expense
import model.ExpenseCategory
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

data class ExpensesUiState(
    val expenses: List<Expense> = emptyList(),
    val total: Double = 0.0
)

class ExpensesViewModel(private val expenseRepository: ExpenseRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(ExpensesUiState())
    val uiState = _uiState.asStateFlow()
    private var allExpenses: MutableList<Expense> = mutableListOf()

    init {
        getAllExpenses()
    }

    private fun updateExpenseList() {
        viewModelScope.launch {
            allExpenses = expenseRepository.getAllExpenses().toMutableList()
            updateState()
        }
    }

    private fun getAllExpenses() {
        expenseRepository.getAllExpenses()
        updateExpenseList()
    }

    fun addExpense(expense: Expense) {
        expenseRepository.addExpense(expense)
        updateExpenseList()
    }

    fun editExpense(expense: Expense) {
        expenseRepository.editExpense(expense)
        updateExpenseList()
    }

    fun deleteExpense(expense: Expense) {
        expenseRepository.deleteExpense(expense)
        updateExpenseList()
    }

    private fun updateState() {
        _uiState.update { state ->
            state.copy(expenses = allExpenses, total = allExpenses.sumOf { it.amount })
        }
    }

    fun getExpenseWithID(id: Long): Expense {
        return allExpenses.first { it.id == id }
    }

    fun getCategories(): List<ExpenseCategory> {
        return expenseRepository.getCategories()
    }
}