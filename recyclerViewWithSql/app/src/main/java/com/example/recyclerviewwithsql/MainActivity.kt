package com.example.recyclerviewwithsql

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_alert_dialog.*

class MainActivity : AppCompatActivity(), clickEvent {
    lateinit var list: ArrayList<Contact>

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list = ArrayList<Contact>()
        addName.setOnClickListener {
            val db = DBHelper(this, null)
            val name = enterName.text.toString()
            val age = enterAge.text.toString()
            db.addName(name, age)
            Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()
            enterName.text.clear()
            enterAge.text.clear()
        }

        printName.setOnClickListener {
            val db = DBHelper(this, null)
            val cursor = db.getName()
            Name.text = "Name\n\n"
            Age.text = "Age\n\n"
            list.clear()

            while (cursor!!.moveToNext()) {
                list.add(
                    Contact(
                        cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL))
                    )
                )
            }

            // at last we close our cursor
            cursor.close()
            recyler.layoutManager = LinearLayoutManager(this)
            recyler.adapter = SqlAdapter(this, list, this)
        }
        delete.setOnClickListener {
            val db = DBHelper(this, null)
            db.delAll()
        }
    }

    override fun clickHuaToKarDo(position: Int) {
        TODO("Not yet implemented")
    }

    override fun longClickHuaToKarDo(position: Int) {
        Toast.makeText(
            this@MainActivity,
            list[position].Age + "Long Click",
            Toast.LENGTH_SHORT
        ).show()
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_alert_dialog)
        dialog.show()
        val prev_name = list[position].Name
        dialog.update.setOnClickListener {
            val name = dialog.nameUpdate.text.toString()
            val age = dialog.ageUpdate.text.toString()
            Log.e(this.toString(), name + age)
            val db = DBHelper(this, null)
            db.updateInDataBase(name, age, prev_name)
            list[position].Name = name
            list[position].Age = age
            dialog.dismiss()
        }
    }
}




