package com.example.familywallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.familywallet.ui.*
import com.example.familywallet.ui.theme.FamilyWalletTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FamilyWalletTheme {
                var currentScreen by remember { mutableStateOf("Dashboard") }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (currentScreen) {
                        "Dashboard" -> DashboardScreen(onNavigate = { currentScreen = it }, modifier = Modifier.padding(innerPadding))
                        "Profile" -> ProfileScreen(onNavigate = { currentScreen = it }, modifier = Modifier.padding(innerPadding))
                        "Statistics" -> StatisticsScreen(onNavigate = { currentScreen = it }, modifier = Modifier.padding(innerPadding))
                        "Settings" -> SettingsScreen(onNavigate = { currentScreen = it }, modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}
