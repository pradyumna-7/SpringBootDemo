package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAgeGreaterThan(int age);

    @Query("select s from Student s where s.name like :prefix%")
    List<Student> findNameLike(String prefix);
}
