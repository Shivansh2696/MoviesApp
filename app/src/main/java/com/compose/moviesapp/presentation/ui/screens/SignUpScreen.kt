package com.compose.moviesapp.presentation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun SignUpScreen(
    onBackToLogin: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }
    var isConPasswordValid by remember { mutableStateOf(true) }

    val context = LocalContext.current
    val enterEmailPasswordText = stringResource(R.string.enter_email_password)
    val enterConfirmPasswordText = stringResource(R.string.enter_con_password)
    val confirmPasswordErrorText = stringResource(R.string.confirm_password_error)
    val registerSuccessText = stringResource(R.string.register_success)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.icon_profile),
            contentDescription = "profile icon",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.register),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        EmailTextField(
            email = email,
            onEmailChange = {
                email = it
                isEmailValid = Utils.validateEmail(email)
            },
            isEmailValid = isEmailValid
        )

        Spacer(modifier = Modifier.height(5.dp))

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

        Spacer(modifier = Modifier.height(5.dp))


        PasswordTextField(
            password = confirmPassword,
            onPasswordChange = {
                confirmPassword = it
                isConPasswordValid = Utils.validateConfirmPassword(password, confirmPassword)
            },
            label = stringResource(R.string.con_password),
            errorMessage = stringResource(R.string.confirm_password_error),
            isPasswordValid = isConPasswordValid,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))

        GradientCircularButton(
            onClick = {
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, enterEmailPasswordText, Toast.LENGTH_SHORT).show()
                    return@GradientCircularButton
                }

                if (confirmPassword.isEmpty()){
                    Toast.makeText(context, enterConfirmPasswordText, Toast.LENGTH_SHORT).show()
                    return@GradientCircularButton
                }

                if (confirmPassword != password){
                    Toast.makeText(context, confirmPasswordErrorText, Toast.LENGTH_SHORT).show()
                    return@GradientCircularButton
                }

                if (isEmailValid && isPasswordValid && isConPasswordValid) {
                    Toast.makeText(context, registerSuccessText, Toast.LENGTH_SHORT).show()
                    onBackToLogin()
                }
            },
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 45.dp)
        )
    }
}
