package com.example.ca

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class EmployeeAdapter(val mctx: Context, val employeeList: ArrayList<EMPLOYEE>, val layout: Int) :
    ArrayAdapter<EMPLOYEE>(mctx, layout, employeeList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mctx)
        val view = layoutInflater.inflate(layout, null)
        val name = view.findViewById<TextView>(R.id.nametv)
        val age = view.findViewById(R.id.agetv) as TextView
        val salary = view.findViewById<TextView>(R.id.salarytv)

        val currentEmp = employeeList[position]
        name.text = currentEmp.name
        age.text = currentEmp.age
        salary.text = currentEmp.salary

        return view
    }
}