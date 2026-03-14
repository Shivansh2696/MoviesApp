package com.compose.moviesapp.presentation.ui.screens

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import com.compose.moviesapp.R
import com.compose.moviesapp.presentation.utils.EmailTextField
import com.compose.moviesapp.presentation.utils.GradientCircularButton
import com.compose.moviesapp.presentation.utils.PasswordTextField
import com.compose.moviesapp.core.Utils

@Composable
fun LoginScreen(
    onNavigateToSignUp: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }

    val context = LocalContext.current
    val enterEmailPasswordText = stringResource(R.string.enter_email_password)
    val loginSuccessfulText = stringResource(R.string.login_successful)

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

        EmailTextField(
            email = email,
            onEmailChange = {
                email = it
                isEmailValid = Utils.validateEmail(email)
            },
            isEmailValid = isEmailValid
        )

        Spacer(modifier = Modifier.height(12.dp))

        PasswordTextField(
            password = password,
            onPasswordChange = {
                password = it
                isPasswordValid = Utils.validatePassword(password)
            },
            label = stringResource(R.string.password),
            errorMessage = stringResource(R.string.password_error),
            isPasswordValid = isPasswordValid,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        GradientCircularButton(
            onClick = {
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, enterEmailPasswordText, Toast.LENGTH_SHORT).show()
                    return@GradientCircularButton
                }

                if (isEmailValid && isPasswordValid) {
                    Toast.makeText(context, loginSuccessfulText, Toast.LENGTH_SHORT).show()
                    onLoginSuccess()
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
