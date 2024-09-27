package com.example.familywallet.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun Menu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    onNavigate: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { onDismiss() },
                offset = DpOffset(x = 0.dp, y = 0.dp),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight()
            ) {
                // TopBar-like header for the menu
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "MENU",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                // Menu items
                DropdownMenuItem(
                    text = { Text("Dashboard") },
                    onClick = {
                        onNavigate("Dashboard")
                        onDismiss()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Profile") },
                    onClick = {
                        onNavigate("Profile")
                        onDismiss()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Statistics") },
                    onClick = {
                        onNavigate("Statistics")
                        onDismiss()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = {
                        onNavigate("Settings")
                        onDismiss()
                    }
                )
            }
        }
    }
}
