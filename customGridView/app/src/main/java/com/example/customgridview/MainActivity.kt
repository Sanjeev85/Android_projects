package com.example.customgridview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var arrayList: ArrayList<gridItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gridview = findViewById<GridView>(R.id.grid_view)
        arrayList = ArrayList()
        arrayList.add(gridItem(R.drawable.fb, "sample1"))
        arrayList.add(gridItem(R.drawable.fb, "sample2"))
        arrayList.add(gridItem(R.drawable.fb, "sample3"))
        arrayList.add(gridItem(R.drawable.fb, "sample4"))

        val arrayAdapter = arrayAdapter(this, R.layout.grid_item, arrayList)
        gridview.adapter = arrayAdapter

        gridview.setOnItemClickListener {
            _, _, p, _ ->
            Toast.makeText(this, arrayList[p].text, Toast.LENGTH_SHORT).show()
        }
    }
}