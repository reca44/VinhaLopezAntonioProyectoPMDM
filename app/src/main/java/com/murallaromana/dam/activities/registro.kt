package com.murallaromana.dam.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.murallaromana.dam.R

class registro : AppCompatActivity() {
    private lateinit var btRegistrarse:Button
    private  lateinit var textIUsuario: TextInputEditText
    private  lateinit var textPass: TextInputEditText
    private  lateinit var textMail: TextInputEditText
    private  lateinit var textDireccion: TextInputEditText
    private  lateinit var textTelefono: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro)
        btRegistrarse=findViewById(R.id.btRegistrarse)
        btRegistrarse.setOnClickListener {onBackPressed()

        textIUsuario=findViewById(R.id.inputUsuario)
        textPass=findViewById(R.id.inputPass)
        textMail=findViewById(R.id.inputMail)
        textDireccion=findViewById(R.id.inputDireccion)
        textTelefono=findViewById(R.id.inputTelefono)

            val sharedPref = getPreferences(Context.MODE_PRIVATE)
            with (sharedPref.edit()) {
                putString("Usuario", textIUsuario.text.toString())
                putString("Password", textPass.text.toString())
                putString("E-mail", textMail.text.toString())
                putString("Direccion", textDireccion.text.toString())
                putString("Telefono", textTelefono.text.toString())
                commit()
            }

          //  val editor = sharedPref.edit()
            //editor.putInt("", 1)

        }

    }
}