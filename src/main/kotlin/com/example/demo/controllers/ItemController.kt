package com.example.demo.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class ItemController {
    private val idCounter = AtomicLong(6) // Start after the last hardcoded ID

    // Get all products
    @GetMapping
    fun getAllProducts(): List<String> {
        return listOf("test")
    }

}