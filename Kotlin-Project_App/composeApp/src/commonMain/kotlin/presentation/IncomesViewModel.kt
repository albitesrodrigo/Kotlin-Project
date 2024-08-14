package presentation

import domain.IncomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.Income
import model.IncomeCategory
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

data class IncomesUiState(
    val incomes: List<Income> = emptyList(),
    val total: Double = 0.0
)

class IncomesViewModel(private val incomeRepository: IncomeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(IncomesUiState())
    val uiState = _uiState.asStateFlow()
    private val allIncome = incomeRepository.getAllIncomes()

    init {
        getAllIncomes()
    }

    private fun updateState() {
        _uiState.update { state ->
            state.copy(incomes = allIncome, total = allIncome.sumOf { it.amount })
        }
    }

    private fun getAllIncomes() {
        viewModelScope.launch {
            updateState()
        }
    }

    fun addIncome(income: Income) {
        viewModelScope.launch {
            incomeRepository.addIncome(income)
            updateState()
        }
    }

    fun editIncome(income: Income) {
        viewModelScope.launch {
            incomeRepository.editIncome(income)
            updateState()
        }
    }

    fun deleteIncome(income: Income) {
        viewModelScope.launch {
            incomeRepository.deleteIncome(income)
            updateState()
        }
    }

    fun getIncomeWithID(id: Long): Income {
        return allIncome.first { it.id == id }
    }

    fun getCategories(): List<IncomeCategory> {
        return incomeRepository.getCategories()
    }
}