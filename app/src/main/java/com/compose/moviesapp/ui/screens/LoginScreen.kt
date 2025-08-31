package com.compose.moviesapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.compose.moviesapp.R
import com.compose.moviesapp.ui.utils.GradientCircularButton
import com.compose.moviesapp.utils.Utils

@Composable
fun LoginScreen(
    onNavigateToSignUp: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isEmailValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_movies),
            contentDescription = stringResource(R.string.login_icon),
            modifier = Modifier
        )
        Text(
            text = stringResource(R.string.login),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp)
        )

        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                isEmailValid = Utils.validateEmail(it)
            },
            label = { Text(stringResource(R.string.username)) },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = stringResource(R.string.email_icon)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            isError = !isEmailValid,
            supportingText = {
                if (!isEmailValid) {
                    Text(
                        text = stringResource(R.string.email_error),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                isPasswordValid = Utils.validatePassword(it)
            },
            label = { Text(stringResource(R.string.password)) },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = stringResource(R.string.password_icon)
                )
            },
            trailingIcon = { // 2. Use trailingIcon
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Localized description for accessibility services
                val description = if (passwordVisible)
                    stringResource(R.string.hide_password)
                else stringResource(R.string.show_password)

                // 3. IconButton for click handling
                IconButton(onClick = { passwordVisible = !passwordVisible }) { // 5. Toggle state
                    Icon(imageVector = image, description) // 4. Conditional icon
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(), // 6. VisualTransformation
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            isError = !isPasswordValid,
            supportingText = {
                if (!isPasswordValid) {
                    Text(
                        text = stringResource(R.string.password_error),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        GradientCircularButton(
            onClick = {
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context,
                        context.getString(R.string.enter_username_password), Toast.LENGTH_SHORT).show()
                    return@GradientCircularButton
                }

                if (isEmailValid && isPasswordValid){
                    Toast.makeText(context,
                        context.getString(R.string.login_successful), Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 45.dp)
        )

        Spacer(modifier = Modifier.height(35.dp))

        Row {
            Text(
                text = stringResource(R.string.don_t_have_an_account),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stringResource(R.string.sign_up),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.clickable {
                    onNavigateToSignUp()
                }
            )
        }
    }
}