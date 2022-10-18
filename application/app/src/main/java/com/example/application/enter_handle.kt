package com.example.application

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class enter_handle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_item)
        var data = ArrayList<Entry>()
        data.add(Entry(0.toFloat(), 25.toFloat()))
        data.add(Entry(1.toFloat(), 33.toFloat()))
        data.add(Entry(12.toFloat(), 34.toFloat()))
        data.add(Entry(0.toFloat(), 21.toFloat()))
        val lc = findViewById<LineChart>(R.id.line_chart)
        val dataset = LineDataSet(data, "first_data")
        dataset.setColor(Color.RED)
        dataset.lineWidth = 2f
        val arr = ArrayList<ILineDataSet>()
        arr.add(dataset)

        val line_data = LineData(arr)
        lc.data = line_data
        lc.isDragEnabled = true
        lc.animateX(2000)
        lc.invalidate()



    }
}