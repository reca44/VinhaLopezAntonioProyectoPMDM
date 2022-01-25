package com.murallaromana.dam.model.entities
import com.google.gson.annotations.SerializedName

class Usuario (
    var id: Int?,
    @SerializedName("email") var correo: String,
    @SerializedName("password") var contrasenha:String
)