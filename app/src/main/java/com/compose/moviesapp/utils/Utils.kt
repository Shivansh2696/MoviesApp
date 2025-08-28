package com.compose.moviesapp.utils

object Utils {

    fun validateEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validatePassword(password: String): Boolean {
        // Add your password validation logic here (e.g., minimum length)
        return password.length >= 8
    }

}