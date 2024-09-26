package com.example.familywallet.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard") },
                navigationIcon = {
                    IconButton(onClick = { expanded = true }) {
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
                modifier = modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Welcome To",
                    fontSize = 20.sp
                )
            }
        }
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            text = { Text("Profile") },
            onClick = {
                expanded = false
                // TODO: Реализовать переход к профилю
            }
        )
        DropdownMenuItem(
            text = { Text("Statistics") },
            onClick = {
                expanded = false
                // TODO: Реализовать переход к экрану статистики
            }
        )
        DropdownMenuItem(
            text = { Text("Settings") },
            onClick = {
                expanded = false
                // TODO: Реализовать переход к настройкам
            }
        )
        DropdownMenuItem(
            text = { Text("Exit") },
            onClick = {
                expanded = false
                // TODO: Реализовать выход из аккаунта
            }
        )
    }
}
