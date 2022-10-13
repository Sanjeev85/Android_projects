package com.example.ca

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import org.w3c.dom.Text

class EmployeeAdapter(val mctx: Context, val employeeList: ArrayList<EMPLOYEE>, val layout: Int) :
    ArrayAdapter<EMPLOYEE>(mctx, layout, employeeList) {

    override fun getPosition(item: EMPLOYEE?): Int {
        return super.getPosition(item)
    }

    override fun getItem(position: Int): EMPLOYEE? {
        return super.getItem(position)
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(mctx).inflate(layout, parent, false)
        val id = view.findViewById<TextView>(R.id.id)
        val name = view.findViewById<TextView>(R.id.name)
        val age = view.findViewById(R.id.age) as TextView
        val salary = view.findViewById<TextView>(R.id.salary)

        val currentEmp = employeeList[position]
        id.text = currentEmp.id.toString()
        name.text = currentEmp.name
        age.text = currentEmp.age.toString()
        salary.text = currentEmp.Salary.toString()

        return view
    }
}