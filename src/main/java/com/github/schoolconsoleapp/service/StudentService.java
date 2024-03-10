package com.github.schoolconsoleapp.service;

import com.github.schoolconsoleapp.entity.Student;
import com.github.schoolconsoleapp.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> findAllStudentsByGroup(String groupName) {
        return studentRepository.findAllStudentsByGroup(groupName);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student add(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteById(long id) {
        studentRepository.deleteStudentCoursesByStudentId(id);
        studentRepository.deleteById(id);
    }

    @Transactional
    public void addStudentToCourse(Long studentId, Long courseId) {
        studentRepository.addStudentToCourse(studentId, courseId);
    }

    @Transactional
    public void deleteStudentFromCourse(Long studentId, Long courseId) {
        studentRepository.deleteStudentFromCourse(studentId, courseId);
    }


}