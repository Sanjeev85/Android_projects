package com.example.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Employee")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val phone: Long
)