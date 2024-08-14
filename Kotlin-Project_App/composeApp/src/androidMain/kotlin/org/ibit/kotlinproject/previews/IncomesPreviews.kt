package org.ibit.kotlinproject.previews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import data.IncomeManager
import model.Income
import model.IncomeCategory
import presentation.IncomesUiState
import ui.AllIncomesHeader
import ui.IcomesScreen
import ui.IncomesItem
import ui.IncomesTotalHeader

@Preview(showBackground = true)
@Composable
private fun IncomesTotalHeaderPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        IncomesTotalHeader(total = 1028.8)
    }
}

@Preview(showBackground = true)
@Composable
private fun AllIncomesHeaderPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        AllIncomesHeader()
    }
}

@Preview(showBackground = true)
@Composable
private fun IncomesItemPreview() {
    Box(modifier = Modifier.padding(8.dp)) {
        IncomesItem(income = IncomeManager.fakeIncomeList[0], onIncomeClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun IcomesScreenPreview() {
    IcomesScreen(
        uiState = IncomesUiState(
            incomes = IncomeManager.fakeIncomeList,
            total = 1028.8),
        onIncomeClick = {}
    )
}