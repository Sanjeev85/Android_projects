package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), setOnItemClickListener {
    lateinit var arrayList: ArrayList<listItem>
    lateinit var recyclerView: RecyclerView
    lateinit var adapterRecycler: adapterRecycler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearListData()
//        Log.d("arrayList size", "ArrayList size = ${arrayList.size}")
        recyclerView = findViewById(R.id.recylerViewLinear)
        recyclerView.setHasFixedSize(true)
        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        adapterRecycler = adapterRecycler(this, arrayList, this)


        recyclerView.adapter = adapterRecycler


    }

    private fun linearListData() {
        arrayList = ArrayList()
//        Log.d("tag", "Inside linearListData")
        arrayList.add(listItem(R.drawable.fb, "Android 25", "25"))
        arrayList.add(listItem(R.drawable.fb, "Android 12", "12"))
        arrayList.add(listItem(R.drawable.fb, "Android 13", "13"))
        arrayList.add(listItem(R.drawable.fb, "Android 14", "14"))
        arrayList.add(listItem(R.drawable.fb, "Android 15", "15"))
        arrayList.add(listItem(R.drawable.fb, "Android 16", "16"))
        arrayList.add(listItem(R.drawable.fb, "Android 17", "17"))
        arrayList.add(listItem(R.drawable.fb, "Android 18", "18"))

    }


    override fun onItemClickListener(listItem: listItem) {

        Toast.makeText(
            this,
            listItem.version.toString() + " " + listItem.versionName.toString(),
            Toast.LENGTH_SHORT
        ).show()
    }
}
