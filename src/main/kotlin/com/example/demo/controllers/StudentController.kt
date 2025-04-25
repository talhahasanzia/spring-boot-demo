package com.example.demo.controllers

import com.example.demo.entity.Student
import com.example.demo.repository.StudentRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/students")
class StudentController(private val studentRepository: StudentRepository) {

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    fun getAllStudents(): List<Student> = studentRepository.findAll()

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    fun createStudent(@RequestBody student: Student): Student = studentRepository.save(student)


    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    fun updateStudent(@PathVariable id: Long, @RequestBody studentDetails: Student): Student {
        val student = studentRepository.findById(id).orElseThrow { Exception("Student not found") }
        return studentRepository.save(student.copy(name = studentDetails.name, email = studentDetails.email))
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable id: Long) {
        studentRepository.deleteById(id)
    }
}