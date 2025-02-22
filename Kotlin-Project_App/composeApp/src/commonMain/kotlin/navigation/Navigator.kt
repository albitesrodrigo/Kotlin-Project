package navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import data.ExpenseRepoImpl
import getColorsTheme
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.viewmodel.viewModel
import org.koin.core.parameter.parametersOf
import presentation.ExpensesViewModel
import ui.ExpensesDetailScreen
import ui.ExpensesScreen
import kotlin.math.exp

@Composable
fun Navigation(navigator: Navigator) {

    val colors = getColorsTheme()

    //    val viewModel = viewModel(modelClass = ExpensesViewModel::class) {
    //        ExpensesViewModel(ExpenseRepoImpl(ExpenseManager))
    //    }
    val viewModel = koinViewModel(ExpensesViewModel::class) { parametersOf() }

    NavHost(
        modifier = Modifier.background(colors.backgroundColor),
        navigator = navigator,
        initialRoute = "/home"
    ) {
        scene(route = "/home") {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ExpensesScreen(uiState) { expense ->
                navigator.navigate("/addExpenses/${expense.id}")
            }
        }
        scene(route = "/addExpenses/{expenseId}?") { backStackEntry ->
            val idFromPath = backStackEntry.path<Long>("expenseId")
            val expenseToEditOrAdd = idFromPath ?.let { id -> viewModel.getExpenseWithID(id) }

            ExpensesDetailScreen(expenseToEdit = expenseToEditOrAdd, categoryList = viewModel.getCategories()) { expense ->
                if (expenseToEditOrAdd == null) {
                    viewModel.addExpense(expense)
                } else {
                    viewModel.editExpense(expense)
                }
                navigator.popBackStack()
            }
        }
    }

}

//fun main() {
//    val car = Car(Engine(50))
//    car.starCart()
//}
//
//
//class Car(private val engine: Engine) {
//    fun starCart() {
//        engine.start()
//    }
//}
//
//class Engine(private val horsePower: Int) {
//    fun start() {
//        println("Se arrancado el motor con $horsePower caballos de potencia")
//    }
//}