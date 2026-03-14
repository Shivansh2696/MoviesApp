package com.compose.moviesapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.compose.moviesapp.data.entities.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
