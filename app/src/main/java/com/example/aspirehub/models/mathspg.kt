package com.example.aspirehub.models

data class MathsPG(var Name:String?=null,
                   val options :List<String>?=null,
                   val description   :String? = null,
                   val img   :String = ""



    )

fun checkMathsPG(mathsPG: MathsPG) {
    when {
        mathsPG.Name.isNullOrBlank() -> {
            println("Name is missing or blank")
        }
        mathsPG.options.isNullOrEmpty() -> {
            println("Options list is missing or empty")
        }
        mathsPG.description.isNullOrBlank() -> {
            println("Description is missing or blank")
        }
        mathsPG.img.isEmpty() -> {
            println("Image URL is empty")
        }
        else -> {
            // If all fields are non-null and valid
            println("MathsPG is valid: $mathsPG")
        }
    }
}
