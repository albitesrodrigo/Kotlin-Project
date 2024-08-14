package ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import getColorsTheme
import model.Income

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IncomesDetailScreen(
    incomeToEdit: Income? = null,
    addIncomeAndNavigateBack: (income: Income) -> Unit,
) {

    val colors = getColorsTheme()
    var price by remember { mutableStateOf(incomeToEdit?.amount ?: 0.0) }
    var description by remember { mutableStateOf(incomeToEdit?.description ?: "") }
    var incomeCategory by remember { mutableStateOf(incomeToEdit?.category?.name ?: "") }
    var categorySelect by remember {
        mutableStateOf(
            incomeToEdit?.category?.name ?: "Selecciona la categoría"
        )
    }
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val keyBoardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(sheetState.targetValue) {
        if (sheetState.targetValue == ModalBottomSheetValue.Expanded) {
            keyBoardController?.hide()
        }
    }
}