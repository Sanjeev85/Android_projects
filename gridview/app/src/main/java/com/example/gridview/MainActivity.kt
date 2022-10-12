package com.example.gridview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = arrayOf("abc", "def", "ghi", "jkl")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
        val gridView = findViewById<GridView>(R.id.grid_view)
        gridView.adapter = adapter

        gridView.setOnItemClickListener{
            _, _, p, _ ->
            Toast.makeText(this, data[p], Toast.LENGTH_SHORT).show()
        }
    }
}