package com.example.recyclerviewwithsql

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row.view.*


interface clickEvent {
    fun clickHuaToKarDo(position: Int)
    fun longClickHuaToKarDo(position: Int)
}


class SqlAdapter(val context: Context, val list: ArrayList<Contact>, val clickHoga: clickEvent) :
    RecyclerView.Adapter<SqlAdapter.Holder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.row, parent, false)
        return Holder(view)
    }

    // findviewBy id before onBindViewHolder
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.textView1
        val age = itemView.textView2

        init {
            itemView.setOnClickListener {
                clickHoga.clickHuaToKarDo(adapterPosition)
            }
            itemView.setOnLongClickListener {
                clickHoga.longClickHuaToKarDo(adapterPosition)
                return@setOnLongClickListener true
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list[position]
        holder.name.text = item.Name
        holder.age.text = item.Age
    }
}

