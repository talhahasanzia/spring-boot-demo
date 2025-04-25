package com.example.demo.entity


import jakarta.persistence.*

@Entity
@Table(name = "students")
data class Student @JvmOverloads constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String = "",
    val email: String = ""
)