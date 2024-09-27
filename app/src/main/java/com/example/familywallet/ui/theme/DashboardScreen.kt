package com.example.familywallet.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.familywallet.ui.theme.FamilyWalletTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(onNavigate: (String) -> Unit, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    var currentBudget by remember { mutableStateOf(0.0) }
    var period by remember { mutableStateOf("Days") }
    var periodValue by remember { mutableStateOf("") }
    val periodOptions = listOf("Days", "Weeks", "Months", "Years")

    // State for the dialog
    var dialogOpen by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard") },
                navigationIcon = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Displaying current budget on a gray background
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Title for budget
                        Text(
                            text = "BUDGET",
                            fontSize = 18.sp,
                            color = Color.Black
                        )

                        Text(
                            text = "$${currentBudget.format(2)}",
                            fontSize = 55.sp,
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = Color.White
                        )

                        // Display the period and period value below the budget
                        Text(
                            text = if (periodValue.isNotEmpty()) "Period: $periodValue $period" else "Period: Not Selected",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Button to open the budget dialog
                Button(onClick = { dialogOpen = true }) {
                    Text("Set Budget")
                }

                // Dialog for setting budget and period
                if (dialogOpen) {
                    BudgetDialog(
                        onDismiss = { dialogOpen = false },
                        onBudgetSet = { newBudget, newPeriod, newPeriodValue ->
                            currentBudget = newBudget
                            period = newPeriod
                            periodValue = newPeriodValue
                            dialogOpen = false
                        },
                        periodOptions = periodOptions
                    )
                }
            }
        }
    )

    // Insert Menu component
    Menu(
        expanded = expanded,
        onDismiss = { expanded = false },
        onNavigate = onNavigate
    )
}

// Dialog to set budget and select period
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetDialog(
    onDismiss: () -> Unit,
    onBudgetSet: (Double, String, String) -> Unit,
    periodOptions: List<String>
) {
    var budgetInput by remember { mutableStateOf(TextFieldValue("")) }
    var additionalInput by remember { mutableStateOf(TextFieldValue("")) }
    var selectedPeriod by remember { mutableStateOf(periodOptions[0]) }
    var expanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Set Your Budget") },
        text = {
            Column {
                TextField(
                    value = budgetInput,
                    onValueChange = { budgetInput = it },
                    label = { Text("Budget Amount") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // TextField for additional input
                TextField(
                    value = additionalInput,
                    onValueChange = { additionalInput = it },
                    label = { Text("Period Value") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Dropdown for period selection
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    TextField(
                        readOnly = true,
                        value = selectedPeriod,
                        onValueChange = {},
                        label = { Text("Select Period") },
                        trailingIcon = {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        periodOptions.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    selectedPeriod = option
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val newBudget = budgetInput.text.toDoubleOrNull() ?: 0.0
                    val newPeriodValue = additionalInput.text
                    onBudgetSet(newBudget, selectedPeriod, newPeriodValue)
                }
            ) {
                Text("Set")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

// Extension for formatting number to two decimal places
fun Double.format(digits: Int) = "%.${digits}f".format(this)

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    FamilyWalletTheme {
        DashboardScreen(onNavigate = {})
    }
}
