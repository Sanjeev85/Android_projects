package com.example.codeforcescompanion

import android.os.BaseBundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //Fetch data

        val dataBndl = fetch_data()

        val btmNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        btmNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(Fragment1(), dataBndl)
                R.id.profile -> replaceFragment(ProfileFragment(), dataBndl)
                R.id.setting -> replaceFragment(SettingFragment(), dataBndl)
                else ->  true
            }
        }
    }

    private fun replaceFragment(fragment: Fragment, data: Bundle): Boolean {
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.frame_layout, fragment)
        trans.commit()
        return true;
    }
    private fun fetch_data() : Bundle{
//        delay
        return Bundle()
    }
}