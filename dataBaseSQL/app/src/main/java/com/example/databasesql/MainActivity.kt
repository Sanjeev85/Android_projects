package com.example.databasesql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    @Suppress("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     // below code is to add on click
        // listener to our add name button
        val addName: Button = findViewById(R.id.addName)
        val printName: Button = findViewById(R.id.printName)
        val enterName: EditText = findViewById(R.id.enterName)
        val enterAge: EditText = findViewById(R.id.enterAge)
        val Name = findViewById<TextView>(R.id.Name)
        val Age: TextView = findViewById(R.id.Age)
        val lv: ListView = findViewById(R.id.lv)
        val del: Button = findViewById(R.id.delete)
        var list = mutableListOf<Contact>()
        addName.setOnClickListener {

            // below we have created
            // a new DBHelper class,
            // and passed context to it
            val db = DBHelper(this, null)

            // creating variables for values
            // in name and age edit texts
            val name = enterName.text.toString()
            val age = enterAge.text.toString()

            // calling method to add
            // name to our database
            db.addName(name, age)

            // Toast to message on the screen
            val show = Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            enterName.text.clear()
            enterAge.text.clear()
        }

        // below code is to add on click
        // listener to our print name button
        printName.setOnClickListener {

            // creating a DBHelper class
            // and passing context to it
            val db = DBHelper(this, null)

            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view
            val cursor = db.getName()
            Name.text = "Name\n\n"
            Age.text = "Age\n\n"
            // moving the cursor to first position and
            // appending value in the text view
            // cursor!!.moveToFirst()
            //  Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
            //  Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")

            // moving our cursor to next
            // position and appending values
            list.clear()

            while (cursor!!.moveToNext()) {
                list.add(
                    Contact(
                        cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL))
                    )
                )
                // Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
                // Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
            }

            // at last we close our cursor
            cursor.close()
            lv.adapter = MyAdapter(this, R.layout.row, list)
        }
        del.setOnClickListener {
            val db = DBHelper(this, null)
            db.delAll()
        }

    }
}



