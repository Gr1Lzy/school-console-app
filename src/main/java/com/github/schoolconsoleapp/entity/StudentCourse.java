package com.github.schoolconsoleapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_courses")
public class StudentCourse {
    @Id
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}