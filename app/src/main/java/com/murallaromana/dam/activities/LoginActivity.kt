package com.murallaromana.dam.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.murallaromana.dam.databinding.ActivityLoginBinding
import com.murallaromana.dam.model.data.SharePreferences

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var preferences: SharePreferences
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        title = "Login"
       // preferences = SharePreferences(applicationContext)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btIniciar.setOnClickListener {
          //  val usuario = preferences.recuperar("email")
            //val pass = preferences.recuperar("pass")

       /*     if (!usuario.equals(binding.inputUsuario.text.toString().trim())) {
                binding.inputUsuario.error = "Usuario incorrecto"
            } else if (!pass.equals(binding.inputPass.text.toString().trim())) {
                binding.inputPass.error = "Contraseña incorrecta"
            } else {*/
                val intent = Intent(this, ListaPeliculasActivity::class.java)
                startActivity(intent)
           // }
        }
        binding.Registrarse.setOnClickListener {
            Toast.makeText(this, "Registro", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

    }
}