package com.example.room

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface EmployeeDao {
    @Insert
    suspend fun insertEmp(employee: Employee)

    @Update
    suspend fun updateEmp(employee: Employee)

    @Delete
    suspend fun deleteEmp(employee: Employee)

    @Query("Select * from Employee")
    fun getEmp(): LiveData<List<Employee>>
}