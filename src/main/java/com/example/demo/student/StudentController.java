package com.example.demo.student;

import com.example.demo.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getStudents() {
        try{
            List<Student> students = studentService.getStudents();
            ApiResponse<List<Student>> response = ApiResponse.success(students);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e) {
            ApiResponse<List<Student>> response = ApiResponse.error("Failed to fetch students: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping
    public ResponseEntity<ApiResponse<Student>> addStudent(@RequestBody Student student) {
        try{
            Student savedStudent = studentService.addStudent(student);
            ApiResponse<Student> response = ApiResponse.success(savedStudent);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse<Student> response = ApiResponse.error("Failed to add student: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/greater-than/{age}")
    public ResponseEntity<ApiResponse<List<Student>>> getStudentsGreaterThanAge(@PathVariable int age){
        try{
            List<Student> students = studentService.getStudentsGreaterThanAge(age);
            ApiResponse<List<Student>> response = ApiResponse.success(students);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<List<Student>> response = ApiResponse.error("Failed to fetch students: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name-like/{prefix}")
    public ResponseEntity<ApiResponse<List<Student>>> findNameLike(@PathVariable String prefix){
        try{
            List<Student> students = studentService.findNameLike(prefix);
            ApiResponse<List<Student>> response = ApiResponse.success(students);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<List<Student>> response = ApiResponse.error("Failed to fetch students: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
