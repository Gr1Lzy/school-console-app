package com.github.schoolconsoleapp.service;

import com.github.schoolconsoleapp.repository.StudentCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentCourseService {
    StudentCourseRepository studentCourseRepository;
    
}