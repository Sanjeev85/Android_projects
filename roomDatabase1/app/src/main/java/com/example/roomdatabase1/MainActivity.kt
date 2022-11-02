package com.example.roomdatabase1

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.get
import androidx.room.Database
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var database: ContactDatabase
    lateinit var listView: ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(this, ContactDatabase::class.java, "ContactDb2").build()
        val displayData = findViewById<Button>(R.id.displayData)
        val addData: FloatingActionButton = findViewById(R.id.addData)

        listView = findViewById(R.id.listView)

        listView.setOnItemLongClickListener { parent, view, position, id ->
            val view = parent.get(position)
            val id = view.findViewById<TextView>(R.id.id).text.toString().toLong()
            val name = view.findViewById<TextView>(R.id.name).text.toString()
            val phone = view.findViewById<TextView>(R.id.phone).text.toString()
            createAlertDialog()
            true
        }

    }

    private fun createAlertDialog() {
        val dialog = Dialog(this@MainActivity)
        dialog.setContentView(R.layout.alert_dialog)
        dialog.show()
        val id = dialog.findViewById<EditText>(R.id.input1)
        val name = dialog.findViewById<EditText>(R.id.input2)
        val number = dialog.findViewById<EditText>(R.id.input3)
        val addData = dialog.findViewById<Button>(R.id.addData)

    }
}