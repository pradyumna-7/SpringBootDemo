package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getStudentsGreaterThanAge(int age) {
        return studentRepository.findByAgeGreaterThan(age);
    }

    public List<Student> findNameLike(String prefix) {
        return studentRepository.findNameLike(prefix);
    }
}
