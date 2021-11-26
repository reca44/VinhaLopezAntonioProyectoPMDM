package com.murallaromana.dam.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.murallaromana.dam.R
import com.murallaromana.dam.activities.LoginActivity.Companion.preferences
import com.murallaromana.dam.databinding.ActivityRegistroBinding
import java.util.regex.Pattern

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btRegistrarse.setOnClickListener {
            //volver al login

            //share preferences
            if (comprobarDatos()) {
                val mail = binding.inputMail.text.toString()
                val pass = binding.inputPass.text.toString()
                preferences.guardar(mail, pass)
                onBackPressed()
            }

        }
    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun comprobarDatos(): Boolean {
        val pass = binding.inputPass.text.toString()
        val pass2 = binding.inputpass2.text.toString()

        if (!validarEmail(binding.inputMail.text.toString())) {
            //mensaje error mail
            Toast.makeText(this, "mail incorrecto", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if (pass.length < 8 || pass.length > 20) {
            //comprobamos que la longitud se encuentre entre 8 y 20
            Toast.makeText(this, "La contraseña no tiene la longitud correcta", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if (pass != pass2) {
            //Mensaje de que las contraseñas no son correctas
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }
}

