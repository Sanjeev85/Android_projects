package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var db: database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext, database::class.java, "mydb").build()
        findViewById<TextView>(R.id.insert).setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                db.EmployeeDao().insertEmp(Employee(0, "str", 4444))
            }
        }
    }
}