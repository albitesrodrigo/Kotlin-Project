import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.ExpenseTitleTopBarTypes
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    PreComposeApp {

        KoinContext {
            val colors = getColorsTheme()

            AppTheme {
                val navigator = rememberNavigator()
                val titleTopBar = getTitleTopAppBar(navigator)
                val idEditOrAddExpense = titleTopBar != ExpenseTitleTopBarTypes.DASHBOARD.value
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            elevation = 0.dp,
                            title = {
                                Text(
                                    text = titleTopBar,
                                    fontSize = 25.sp,
                                    color = colors.textColor
                                )
                            },
                            navigationIcon = {
                                if (idEditOrAddExpense) {
                                    IconButton(
                                        onClick = {
                                            navigator.popBackStack()
                                        },
                                    ) {
                                        Icon(
                                            modifier = Modifier.padding(start = 16.dp),
                                            imageVector = Icons.Default.ArrowBack,
                                            tint = colors.textColor,
                                            contentDescription = "Back arrow icon",
                                        )
                                    }
                                } else {
                                    Icon(
                                        modifier = Modifier.padding(start = 16.dp),
                                        imageVector = Icons.Default.Apps,
                                        tint = colors.textColor,
                                        contentDescription = "Dashboard Icon",
                                    )
                                }
                            },
                            backgroundColor = colors.backgroundColor
                        )
                    },
                    floatingActionButton = {
                        if (!idEditOrAddExpense) {
                            FloatingActionButton(
                                modifier = Modifier.padding(8.dp),
                                onClick = {
                                    navigator.navigate("/addExpenses")
                                },
                                shape = RoundedCornerShape(50),
                                backgroundColor = colors.addIconColor,
                                contentColor = Color.White
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    tint = Color.White,
                                    contentDescription = "Floating icon",
                                )
                            }
                        }
                    }
                ) {
                    Navigation(navigator)
                }
            }
        }
    }
}

@Composable
fun getTitleTopAppBar(navigator: Navigator): String {
    var titleTopBar = ExpenseTitleTopBarTypes.DASHBOARD

    val isOnAddExpense =
        navigator.currentEntry.collectAsState(null).value?.route?.route.equals("/addExpenses/{expenseId}?")
    if (isOnAddExpense) {
        titleTopBar = ExpenseTitleTopBarTypes.ADD
    }

    val isOnEditExpense =
        navigator.currentEntry.collectAsState(null).value?.path<String>("expenseId")
    isOnEditExpense?.let {
        titleTopBar = ExpenseTitleTopBarTypes.EDIT
    }

    return titleTopBar.value
}
