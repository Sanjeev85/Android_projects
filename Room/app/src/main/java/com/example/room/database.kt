package com.example.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Employee::class], version = 1)
abstract class database : RoomDatabase() {
    abstract fun EmployeeDao(): EmployeeDao
}