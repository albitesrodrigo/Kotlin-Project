import data.ExpenseManager
import data.ExpenseRepoImpl
import model.Expense
import model.ExpenseCategory
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ExpenseRepoTest {

    private val expenseManager = ExpenseManager
    private val expenseRepo = ExpenseRepoImpl(expenseManager)

    @Test
    fun expense_list_is_not_empty() {
        //GIVEN
        val expenseList = mutableListOf<Expense>()

        //WHEN
        expenseList.addAll(expenseRepo.getAllExpenses())

        //THEN
        assertTrue(expenseList.isNotEmpty())
    }

    @Test
    fun add_new_expense() {
        //GIVEN
        val expenseList = expenseRepo.getAllExpenses()

        //WHEN
        expenseRepo.addExpense(
            Expense(
                amount = 100.0,
                category = ExpenseCategory.TRANSPORTE,
                description = "Combustible"
            )
        )

        //THEN
        assertContains(expenseList, expenseList.find { it.id == 8L })
    }

    @Test
    fun edit_expense() {
        //GIVEN
        val expenseListBefore = expenseRepo.getAllExpenses()

        //WHEN
        // Creamos un nuevo elemento
        val newExpenseId = 7L
        expenseRepo.addExpense(
            Expense(
                amount = 100.0,
                category = ExpenseCategory.TRANSPORTE,
                description = "Combustible"
            )
        )
        assertNotNull(expenseListBefore.find { it.id == newExpenseId })

        // Actualizamos el elemento
        val updateExpense = Expense(
            id = newExpenseId,
            amount = 85.0,
            category = ExpenseCategory.FIESTA,
            description = "Kennedy"
        )
        expenseRepo.editExpense(updateExpense)

        //THEN
        val expenseListAfter = expenseRepo.getAllExpenses()
        // Verificamos que el elemento se haya actualizado
        assertEquals(updateExpense, expenseListAfter.find { it.id == newExpenseId })
    }

    @Test
    fun get_all_categories() {
        // GIVEN
        val categoryList = mutableListOf<ExpenseCategory>()

        // WHEN
        categoryList.addAll(expenseRepo.getCategories())

        // THEN
        assertTrue(categoryList.isNotEmpty())
    }

    @Test
    fun check_all_categories() {
        // GIVEN
        val allCategories = listOf(
            ExpenseCategory.COMESTIBLES,
            ExpenseCategory.FIESTA,
            ExpenseCategory.SNACKS,
            ExpenseCategory.CAFES,
            ExpenseCategory.RESTAURANTES,
            ExpenseCategory.TRANSPORTE,
            ExpenseCategory.CASA,
            ExpenseCategory.OTRO
        )
        val repoCategories = expenseRepo.getCategories()

        // THEN
        assertEquals(allCategories, repoCategories)


    }
}