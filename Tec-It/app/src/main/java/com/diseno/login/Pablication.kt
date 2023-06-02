package com.diseno.login

data class Publication(
    val idpubli: Int?,
    val titulo: String,
    val descripcion: String?,
    val idproducto: Int,
    val idvendedor: Int,
    val imgpub: String,
    val active: Boolean
)