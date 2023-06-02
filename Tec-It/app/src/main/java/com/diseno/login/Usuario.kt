package com.diseno.login

data class Usuario(
    val answer: String,
    val create_time: String,
    val deleted_time: Any?,
    val email: String,
    val idusuario: Int,
    val user_pass: String,
    val question: String,
    val isVendedor: Boolean
)