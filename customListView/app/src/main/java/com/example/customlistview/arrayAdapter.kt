package com.example.customlistview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes

class arrayAdapter(
    var mCtx: Context,
    @LayoutRes private val layoutResource: Int,
    var arrayList: ArrayList<listItem>
) :
    ArrayAdapter<listItem>(mCtx, layoutResource, arrayList) {
    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getCount(): Int {
        return super.getCount()
    }

    override fun getItem(position: Int): listItem? {
        return super.getItem(position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        Log.e("Hello", "Inside getView arrayAdapter")
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResource, null)
        val img = view?.findViewById<ImageView>(R.id.facebook_img)
        val version = view?.findViewById<TextView>(R.id.version)
        val versionName = view?.findViewById<TextView>(R.id.version_name)

        val item = arrayList.get(position)

        img?.setImageResource(item.image_resource!!)
        version?.text = item.version!!
        versionName?.text = item.versionName!!
        return view
    }
}