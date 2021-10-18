package com.murallaromana.dam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    private lateinit var btRegistrarse: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btRegistrarse = findViewById(R.id.Registrarse)
        btRegistrarse.setOnClickListener {
            Toast.makeText(this, "Registro", Toast.LENGTH_SHORT).show()

            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
    }}
}