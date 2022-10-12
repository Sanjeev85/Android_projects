package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapterRecycler(
    context: Context,
    arrayList: ArrayList<listItem>,
    itemClickListener: setOnItemClickListener
) :
    RecyclerView.Adapter<adapterRecycler.MyHolder>() {
    var context: Context
    var arrayList: ArrayList<listItem>
    lateinit var itemClickListener: setOnItemClickListener


    init {
        this.context = context
        this.arrayList = arrayList
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        holder.versionName.text = arrayList[position].versionName
        holder.version.text = arrayList[position].version
        holder.image.setImageResource(arrayList[position].image_resource)


        //bind every holder item using bind function and itemClickListener
        holder.bind(arrayList[position], itemClickListener)

    }

    class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var versionName: TextView
        lateinit var version: TextView
        lateinit var image: ImageView

        init {
            versionName = view.findViewById(R.id.version_name)
            version = view.findViewById(R.id.version)
            image = view.findViewById(R.id.facebook_img)
        }


        // this will bind to on every item
        fun bind(singleItem: listItem, clickListener: setOnItemClickListener) {
            itemView.setOnClickListener {
                clickListener.onItemClickListener(singleItem)
            }
        }
    }
}


//interface for onItemClickListener
interface setOnItemClickListener {
    fun onItemClickListener(listItem: listItem)
}