package com.example.ca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val add_data: Button = findViewById(R.id.add_data)
        val retreive_data: Button = findViewById(R.id.retrieve_data)
        val enterName: EditText = findViewById(R.id.nameET)
        val enterAge: EditText = findViewById(R.id.ageEt)
        val enterSalary: EditText = findViewById(R.id.salaryET)
        val Name: TextView = findViewById(R.id.name)
        val Age: TextView = findViewById(R.id.age)
        val lv: ListView = findViewById(R.id.lv)
        var list = ArrayList<EMPLOYEE>()

    }
}