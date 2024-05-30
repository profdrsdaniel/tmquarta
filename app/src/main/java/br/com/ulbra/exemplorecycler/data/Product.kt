package br.com.ulbra.exemplorecycler.data

import java.io.Serializable

data class Product(
    val urlImage: String,
    val name: String,
    val price: String
): Serializable
