package com.example.demo.controllers

import com.example.demo.entity.Student
import com.example.demo.repository.StudentRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "Student API", description = "Operations related to students")
@RestController
@RequestMapping("/api/students")
class StudentController(private val studentRepository: StudentRepository) {

    @Operation(summary = "Get all students", description = "Retrieve a list of all students")
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    fun getAllStudents(): List<Student> = studentRepository.findAll()

    @Operation(summary = "Create a new student", description = "Add a new student to the database")
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    fun createStudent(@RequestBody student: Student): Student = studentRepository.save(student)

    @Operation(summary = "Update a student", description = "Update the details of an existing student")
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    fun updateStudent(@PathVariable id: Long, @RequestBody studentDetails: Student): Student {
        val student = studentRepository.findById(id).orElseThrow { Exception("Student not found") }
        return studentRepository.save(student.copy(name = studentDetails.name, email = studentDetails.email))
    }

    @Operation(summary = "Delete a student", description = "Remove a student from the database")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable id: Long) {
        studentRepository.deleteById(id)
    }
}
