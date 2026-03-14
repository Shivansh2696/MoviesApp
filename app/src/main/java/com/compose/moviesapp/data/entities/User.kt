package com.compose.moviesapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @PrimaryKey
    val userName : String,
    val password : String
)