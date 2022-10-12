package com.example.customlistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.view.get

class MainActivity : AppCompatActivity() {
    lateinit var array: ArrayList<listItem>
    lateinit var customListView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        array = ArrayList()
        customListView = findViewById(R.id.list_view)
        array.add(listItem(R.drawable.fb, "Poor King", "Jinga Gang"))
        array.add(listItem(R.drawable.fb, "Poor King", "Jinga Gang"))
        array.add(listItem(R.drawable.fb, "Poor King", "Jinga Gang"))
        array.add(listItem(R.drawable.fb, "Poor King", "Jinga Gang"))

        val adapter = arrayAdapter(this, R.layout.custom_list_view, array)
        customListView.adapter = adapter

        customListView.onItemClickListener = AdapterView.OnItemClickListener{
            parent, _, p, _ ->
            Toast.makeText(this, array[p].versionName, Toast.LENGTH_SHORT).show()
        }

    }
}