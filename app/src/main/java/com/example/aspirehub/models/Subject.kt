package com.example.aspirehub.models

data class Subject(val name: String? = null,val img: String? = null, val degree: List<Degree>? = null)

data class Degree(val name: String? = null, val options: List<Course>? = null,val img: String = "")



data class Course(
    val name: String? = null,
    val img: String? = null,
    val introduction: List<String> = emptyList(),
    val eligibility: List<String> = emptyList(),
    val entryPath: List<String> = emptyList(),
    val institutions: List<String> = emptyList(),
    val jobRoles: List<String> = emptyList(),
    val workPlace: String? = null,
    val expectedSalary: String? = null,
    val fees: String? = null
)


