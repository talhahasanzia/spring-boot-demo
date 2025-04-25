package com.example.demo.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class AppUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val username: String = "",
    val password: String = "",

    val roles: String = "USER" // comma-separated roles if needed
)
