package com.example.asynctask

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var pb: ProgressBar
    lateinit var lv: ListView
    var array = arrayOf("1", "2", "3", "4", "5", "6", "7")
    lateinit var al: ArrayList<String?>
    lateinit var ad: ArrayAdapter<String?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pb = findViewById(R.id.pb)
        lv = findViewById(R.id.lv)
        al = ArrayList()
        ad = ArrayAdapter(this, android.R.layout.simple_list_item_1, al)
        lv.adapter = ad
        myTask().execute()
    }


    internal inner class myTask : AsyncTask<Void?, Int?, String>() {
        var count = 0
        override fun onPreExecute() {
            super.onPreExecute()
            pb.max = 7
            pb.progress = 0
            pb.visibility = View.VISIBLE
            count = 0
        }

        override fun doInBackground(vararg p0: Void?): String {
            for (i in 1..7) {
                count++
                publishProgress(i)
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            return "Completed"
        }

        override fun onProgressUpdate(vararg values: Int?) {
//            super.onProgressUpdate(*values)
            pb.progress = values[0]!!
            al.add(array[count - 1])
            ad.notifyDataSetChanged()

        }

        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
            Toast.makeText(this@MainActivity, result, Toast.LENGTH_SHORT).show()
            pb.visibility = View.INVISIBLE
        }
    }
}