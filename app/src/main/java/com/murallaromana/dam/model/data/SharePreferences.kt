package com.murallaromana.dam.model.data

import android.content.Context
import android.content.SharedPreferences

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
}