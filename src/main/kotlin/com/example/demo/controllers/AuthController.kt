package com.example.demo.controllers

import com.example.demo.entity.AppUser
import com.example.demo.repository.UserRepository
import com.example.demo.util.JwtUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil,
    private val passwordEncoder: PasswordEncoder
) {
    data class AuthRequest(val username: String, val password: String)
    data class AuthResponse(val token: String)

    @PostMapping("/register")
    fun register(@RequestBody request: AuthRequest): ResponseEntity<Any> {
        if (userRepository.findByUsername(request.username) != null)
            return ResponseEntity.badRequest().body("User already exists")
        val user = AppUser(
            username = request.username,
            password = passwordEncoder.encode(request.password)
        )
        userRepository.save(user)
        return ResponseEntity.ok("User registered")
    }

    @PostMapping("/login")
    fun login(@RequestBody request: AuthRequest): ResponseEntity<AuthResponse> {
        val user = userRepository.findByUsername(request.username)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        if (!passwordEncoder.matches(request.password, user.password))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()

        val token = jwtUtil.generateToken(user.username, user.roles)
        return ResponseEntity.ok(AuthResponse(token))
    }
}
