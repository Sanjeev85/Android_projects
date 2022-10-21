package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var database: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database =
            Room.databaseBuilder(applicationContext, ContactDatabase::class.java, "ContactDb1")
                .build()
        GlobalScope.launch {
            database.apply {
                contactDao().apply {
                    insertContact(Contact(0, "entry1", 1100))
                }
            }
        }
    }

    fun printData(view: View) {
        /*
        database.contactDao().getContact().observe(this) {
            Log.e("Room", it.toString())
        }
         */
        database.apply {
            contactDao().apply {
                getContact().observe(this@MainActivity) {
                    Log.e("Room", it.toString())
                }
            }
        }
    }
}