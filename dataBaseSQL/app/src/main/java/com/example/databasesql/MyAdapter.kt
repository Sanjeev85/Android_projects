package com.example.databasesql

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class MyAdapter(var mCtx: Context, var resources: Int, var items: List<Contact>) :
    ArrayAdapter<Contact>(mCtx, resources, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resources, null)

        val name: TextView = view.findViewById(R.id.textView1)
        val age: TextView = view.findViewById(R.id.textView2)

        val mItem: Contact = items[position]
        //imageView.setImageDrawable(mCtx.resources.getDrawable(mItem.img))
        name.text = mItem.Name
        age.text = mItem.Age


        return view
    }
}