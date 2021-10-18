package com.murallaromana.dam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var btRegistrarse:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btRegistrarse=findViewById(R.id.btRegistrarse)
        btRegistrarse.setOnClickListener {onBackPressed()
        }

    }
}