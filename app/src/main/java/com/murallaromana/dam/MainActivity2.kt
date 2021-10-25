package com.murallaromana.dam

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity2 : AppCompatActivity() {
    private lateinit var btRegistrarse: Button
    private lateinit var btIniciar: Button
    private  lateinit var textIUsuario: TextInputEditText
    private  lateinit var textIpassLogin: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btRegistrarse = findViewById(R.id.Registrarse)
        btIniciar=findViewById(R.id.btIniciar)
        btRegistrarse.setOnClickListener {
            Toast.makeText(this, "Registro", Toast.LENGTH_SHORT).show()
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
    }

        btIniciar.setOnClickListener{
        textIUsuario=findViewById(R.id.inputUsuario)
        textIpassLogin=findViewById(R.id.inputPass)
            val sharedPref =getPreferences(MODE_PRIVATE)
           // val defaultValue = resources.getString(R.integer.saved_high_score_default_key)
            val highScore = sharedPref.getString("Usuario", "")
            textIUsuario.setText(highScore)
        }

    }
}