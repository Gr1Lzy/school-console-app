package com.github.schoolconsoleapp;

import com.github.schoolconsoleapp.entity.Group;
import com.github.schoolconsoleapp.entity.Student;
import com.github.schoolconsoleapp.service.CourseService;
import com.github.schoolconsoleapp.service.GroupService;
import com.github.schoolconsoleapp.service.StudentCourseService;
import com.github.schoolconsoleapp.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
@RequiredArgsConstructor
public class Terminal implements CommandLineRunner {
    private final GroupService groupService;
    private final CourseService courseService;
    private final StudentService studentService;
    private final StudentCourseService studentCourseService;
    private final Logger logger = LoggerFactory.getLogger(Terminal.class);
    Scanner scanner = new Scanner(System.in);
    @Override
    public void run(String... args) throws Exception {

        while (true) {
            System.out.println("Select an option:");
            System.out.println("a. Find all groups with less or equal students’ number");
            System.out.println("b. Find all students related to the course with the given name");
            System.out.println("c. Add a new student");
            System.out.println("d. Delete a student by the STUDENT_ID");
            System.out.println("e. Add a student to the course (from a list)");
            System.out.println("f. Remove the student from one of their courses");
            System.out.println("x. Exit");

            String option = scanner.next();

            switch (option) {
                case "a" -> findAllGroupsWithLessOrEqualStudentsNumber();
                case "b" -> findAllStudentsByGroup();
                case "c" -> addNewStudent();
                case "d" -> deleteStudentById();
                case "e" -> addStudentToCourse();
                case "f" -> removeStudentFromCourse();
                case "x" -> {
                    return;
                }
                default -> System.out.println("Invalid option, please try again.");
            }

        }
    }

    private void removeStudentFromCourse() {
        try {
            long studentId = scanner.nextLong();
            long courseId = scanner.nextLong();
            studentService.deleteStudentFromCourse(studentId, courseId);
        } catch (Exception e) {
            logger.error("Error occurred while removing student from course: {}", e.getMessage());
        }
    }

    private void addStudentToCourse() {
        try {
            System.out.println("All courses that we have:");
            System.out.println(courseService.getAllCourses());
            long studentId = scanner.nextLong();
            long courseId = scanner.nextLong();
            studentService.addStudentToCourse(studentId, courseId);
        } catch (Exception e) {
            logger.error("Error occurred while adding student to course: {}", e.getMessage());
        }
    }

    private void deleteStudentById() {
        try {
            System.out.println("All students that we have:");
            System.out.println(studentService.getAllStudents());
            System.out.println("Enter the student ID:");
            long studentId = scanner.nextLong();
            
            studentService.deleteById(studentId);
        } catch (Exception e) {
            logger.error("Error occurred while adding student to course: {}", e.getMessage());
        }
    }

    private void addNewStudent() {
        try {
            System.out.println("You can add a new student:");
            Student student = new Student();
            System.out.println("Write first name:");
            student.setFirstName(scanner.next("[a-zA-Z]+"));
            System.out.println("Write last name:");
            student.setLastName(scanner.next("[a-zA-Z]+"));
            System.out.println("Write group name from this list:");
            System.out.println(groupService.findAll());
            String groupName = scanner.next("[a-zA-Z]{2}-\\\\d{2}");
            Group group = groupService.getByName(groupName);
            if (group != null) {
                student.setGroup(group);
                studentService.add(student);
            } else {
                logger.info("Group with this name does not exist");
            }
        } catch (Exception e) {
            logger.error("Error occurred while adding new student: {}", e.getMessage());
        }
    }

    private void findAllStudentsByGroup() {
        try {
            System.out.println("Enter the group name:");
            System.out.println(groupService.findAll());
            String groupName = scanner.next("[a-zA-Z]{2}-\\\\d{2}");
            System.out.println("All students from this group:\n"
                    + studentService.findAllStudentsByGroup(groupName));
        } catch (Exception e) {
            logger.error("Error occurred while finding students by group: {}", e.getMessage());
        }
    }

    private void findAllGroupsWithLessOrEqualStudentsNumber() {
        try {
            System.out.println("Enter the maximum number of students:");
            int maxStudents = scanner.nextInt();
            System.out.println("Groups with less or equal students’ number:\n"
                    + groupService.findByStudentCountLessThanEqual(maxStudents));
        } catch (Exception e) {
            logger.error("Error occurred while finding groups by student count: {}", e.getMessage());
        }
    }
}
