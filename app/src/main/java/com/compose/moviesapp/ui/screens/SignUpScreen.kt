package com.compose.moviesapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SignUpScreen(
    onBackToLogin: () -> Boolean
) {
    Text(
        text = "SignUp",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 25.dp, top = 50.dp)
    )
}