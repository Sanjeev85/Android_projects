package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val array = arrayOf("abc", "def", "ghi")
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array)
        val lv = findViewById<ListView>(R.id.listV)
        lv.adapter = arrayAdapter

        //onclick listener on item
        lv.setOnItemClickListener{
            _,_,p,_ ->
            Toast.makeText(this, array[p], Toast.LENGTH_SHORT).show()
        }
    }
}