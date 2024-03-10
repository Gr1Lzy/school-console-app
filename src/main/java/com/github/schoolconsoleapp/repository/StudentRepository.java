package com.github.schoolconsoleapp.repository;

import com.github.schoolconsoleapp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.group.groupName = :groupName")
    List<Student> findAllStudentsByGroup(String groupName);

    @Query(value = "INSERT INTO student_courses (student_id, course_id) VALUES (:studentId, :courseId);", nativeQuery = true)
    void addStudentToCourse(Long studentId, Long courseId);

    @Modifying
    @Query(value = "DELETE FROM student_courses WHERE student_id = :studentId AND course_id = :courseId", nativeQuery = true)
    void deleteStudentFromCourse(Long studentId, Long courseId);

    @Modifying
    @Query(value = "DELETE FROM student_courses WHERE student_id = :studentId", nativeQuery = true)
    void deleteStudentCoursesByStudentId(Long studentId);
}