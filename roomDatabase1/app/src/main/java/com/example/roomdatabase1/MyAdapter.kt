package com.example.roomdatabase1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class ContactAdapter(private val mContext: Context, items: MutableList<Contact>) :
    ArrayAdapter<Contact>(mContext, android.R.layout.simple_list_item_2, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(mContext)
                .inflate(android.R.layout.simple_list_item_2, parent, false)!!
        }

        val item = getItem(position)!!

        view.findViewById<TextView>(android.R.id.text1).text = item.name
        view.findViewById<TextView>(android.R.id.text2).text = item.phone.toString()

        return view
    }
}