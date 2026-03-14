package com.compose.moviesapp.presentation.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.compose.moviesapp.R

@Composable
fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
    isEmailValid: Boolean,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = email,
        onValueChange = {
            onEmailChange(it)
        },
        label = { Text(stringResource(R.string.email)) },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = stringResource(R.string.email_icon)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        isError = email.isNotEmpty() && !isEmailValid,
        supportingText = {
            if (email.isNotEmpty() && !isEmailValid) {
                Text(
                    text = stringResource(R.string.email_error),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    )
}
