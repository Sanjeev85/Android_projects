package com.example.ca

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val add_data: Button = findViewById(R.id.add_data)
        val retreive_data: Button = findViewById(R.id.retrieve_data)
        val enterName: EditText = findViewById(R.id.nameET)
        val enterAge: EditText = findViewById(R.id.ageEt)
        val enterSalary: EditText = findViewById(R.id.salaryET)
        val Name: TextView = findViewById(R.id.Name)
        val Age: TextView = findViewById(R.id.Age)
        val Salary: TextView = findViewById(R.id.Salary)
        val lv: ListView = findViewById(R.id.lv)
        var list = ArrayList<EMPLOYEE>()

        add_data.setOnClickListener {
            val db = DBHelper(this, null)

            val name = enterName.text.toString()
            val age = enterAge.text.toString()
            val salary = enterSalary.text.toString()

            db.addData(name, age, salary)
            db.close()

            Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()

            enterName.text.clear()
            enterAge.text.clear()
            enterSalary.text.clear()
        }

        retreive_data.setOnClickListener {

            val db = DBHelper(this, null)
            db.readableDatabase
            val cursor = db.retrieve_data()
            Name.text = "Name\n\n"
            Age.text = "Age\n\n"
            Salary.text = "Salary\n\n"

            list.clear()

            while (cursor!!.moveToNext()) {
                list.add(
                    EMPLOYEE(
                        cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.SALARY_COL))
                    )
                )
            }

            cursor.close()
//            Log.e()
//            for(i in list) {
                Log.e("ERRORRRR", list.toString())
//            }
            lv.adapter = EmployeeAdapter(this, list, R.layout.employee_)
        }
    }
}