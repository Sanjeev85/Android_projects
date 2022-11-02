package com.example.roomdatabase1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import org.w3c.dom.Text

class MyAdapter(val mCtx: Context, val resource: Int, val items: List<Contact>) :
    ArrayAdapter<Contact>(mCtx, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resource, null)

        val name: TextView = view.findViewById(R.id.input1)
        val phone: TextView = view.findViewById(R.id.input2)
        val id: TextView = view.findViewById(R.id.input3)
        val mItem = items[position]

        name.text = mItem.name
        phone.text = mItem.phone.toString()
        id.text = mItem.id.toString()

        return view
    }
}