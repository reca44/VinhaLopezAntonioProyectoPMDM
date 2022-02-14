package com.murallaromana.dam.model.data

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.content.edit
import com.murallaromana.dam.activities.LoginActivity
import java.util.prefs.Preferences

class SharePreferences(val context: Context) {
    private val archivoSP = "SharedPreferences"
    private val preferences: SharedPreferences = context.getSharedPreferences(archivoSP, 0)

    fun guardar(email: String, pass: String) {

        preferences.edit().putString("email", email.trim()).apply()
        preferences.edit().putString("pass", pass.trim()).apply()
    }

    fun recuperar(datoARecuperar: String): String? {

        if (datoARecuperar == "email") {
            return preferences.getString("email", null)
        } else if (datoARecuperar == "pass") {
            return preferences.getString("pass", null)
        }
        return null
    }
    fun guardartoken(token: String?) {
        preferences.edit{
            clear()
            putString("token",token)
            commit()
        }
    }
    fun llamarToken(token: String?): String? {
        return preferences.getString("token",token)
    }
    fun cerrarSesion(context: Context){
        this.guardartoken("")
        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }
}