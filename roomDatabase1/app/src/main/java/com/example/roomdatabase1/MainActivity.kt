package com.example.roomdatabase1

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.view.get
import androidx.room.Database
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var database: ContactDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database =
            Room.databaseBuilder(applicationContext, ContactDatabase::class.java, "ContactDB")
                .build()

        val listView = findViewById<ListView>(R.id.lvContact)
        val adapter = ContactAdapter(this, mutableListOf())
        listView.adapter = adapter

        listView.setOnItemLongClickListener { _, _, position, _ ->
            val item = adapter.getItem(position)!!

            GlobalScope.launch(Dispatchers.IO) {
                database.contactDao().deleteContact(item)
            }
            true
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val (id, name, phone) = adapter.getItem(position)!!
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Edit Contact")

            val etName = EditText(this)
            etName.setText(name)
            val etPhone = EditText(this)
            etPhone.setText(phone.toString())

            val layout = LinearLayout(this)
            layout.orientation = LinearLayout.VERTICAL
            layout.addView(etName)
            layout.addView(etPhone)
            builder.setView(layout)
            builder.setPositiveButton("Update") { dialog, _ ->
                val name = etName.text.toString()
                val phone = etPhone.text.toString().toLong()
                GlobalScope.launch(Dispatchers.IO) {
                    database.contactDao().updateContact(Contact(id, name, phone))
                }
                dialog.dismiss()
            }
            builder.setNegativeButton("Cancel") { it, _ -> it.dismiss() }
            builder.create().show()
        }

        findViewById<FloatingActionButton>(R.id.btnInsert).setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Insert Contact")
            val etName = EditText(this)
            val etPhone = EditText(this)
            val layout = LinearLayout(this)
            layout.orientation = LinearLayout.VERTICAL
            layout.addView(etName)
            layout.addView(etPhone)
            builder.setView(layout)
            builder.setPositiveButton("Insert") { dialog, _ ->
                val name = etName.text.toString()
                val phone = etPhone.text.toString().toLong()
                GlobalScope.launch(Dispatchers.IO) {
                    database.contactDao().insertContact(Contact(0, name, phone))
                }
                dialog.dismiss()
            }
            builder.setNegativeButton("Cancel") { it, _ -> it.dismiss() }
            builder.create().show()
        }


        database.contactDao().getContact().observe(this) {
            adapter.clear()
            adapter.addAll(it)
        }
    }
}
