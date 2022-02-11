package com.murallaromana.dam.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.murallaromana.dam.RetrofitClient
import com.murallaromana.dam.databinding.ActivityLoginBinding
import com.murallaromana.dam.model.data.SharePreferences
import com.murallaromana.dam.model.entities.Token
import com.murallaromana.dam.model.entities.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var preferences: SharePreferences
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.inputUsuario.setText("prueba@gmail.com")
        binding.inputPass.setText("1234")
        binding.btIniciar.setOnClickListener {
        binding.btIniciar.isEnabled=false
        preferences = SharePreferences(applicationContext)
        val u = Usuario(binding.inputUsuario.text.toString(),binding.inputPass.text.toString())
        val loginCall= RetrofitClient.apiRetrofit.login(u)
        val login=this
        loginCall.enqueue(object : Callback<Token>{
            override fun onFailure(call: Call<Token>, t: Throwable) {
                Log.d("Fallo autenticacion", t.toString())

            }
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                Log.d("respuesta: onResponse", response.toString())
                if (response.code()>299 || response.code()<200){
                    Toast.makeText(login, "Error, no se ha podido loguear el usuario", Toast.LENGTH_SHORT)
                        .show()
                }else{
                    val token=response.body()?.token
                    Log.d("respuesta: token:", token.orEmpty())
                    Toast.makeText(login, "Usuario Logeado", Toast.LENGTH_SHORT)
                        .show()
                    preferences.guardartoken(token)
                    val intent = Intent(login, ListaPeliculasActivity::class.java)
                    startActivity(intent)
                }
            }})
        title = "Login"



          //  val usuario = preferences.recuperar("email")
            //val pass = preferences.recuperar("pass")

       /*     if (!usuario.equals(binding.inputUsuario.text.toString().trim())) {
                binding.inputUsuario.error = "Usuario incorrecto"
            } else if (!pass.equals(binding.inputPass.text.toString().trim())) {
                binding.inputPass.error = "Contraseña incorrecta"
            } else {*/


           // }
        }
        binding.Registrarse.setOnClickListener {
            Toast.makeText(this, "Registro", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
        }

    override fun onResume() {
        binding.btIniciar.isEnabled=true
        super.onResume()
    }
    }
