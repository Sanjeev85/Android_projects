package com.example.codeforcescompanion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.codeforcescompanion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ed : EditText = findViewById(R.id.handle)
        val fetch_u : Button = findViewById(R.id.fetch_user)

        fetch_u.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

}
/*
*         binding.btn1.setOnClickListener {
            replaceFragment(Fragment1())
        }
        binding.btn2.setOnClickListener {
            replaceFragment(Fragment2())
        }
*
*    private fun replaceFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_cont, fragment)
        fragmentTransaction.commit()
    }
*
* */