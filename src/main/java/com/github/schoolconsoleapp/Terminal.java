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
    private Logger logger = LoggerFactory.getLogger(Terminal.class);
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
        studentService.deleteStudentFromCourse(scanner.nextLong(), scanner.nextLong());
    }

    private void addStudentToCourse() {
        System.out.println("All courses that we have:");
        System.out.println(courseService.getAllCourses());
        studentService.addStudentToCourse(scanner.nextLong(), scanner.nextLong());
    }

    private void deleteStudentById() {
        studentService.deleteById(scanner.nextLong());
    }

    private void addNewStudent() {
        System.out.println("You can add new student:");
        Student student = new Student();
        System.out.println("Write first name:");
        student.setFirstName(scanner.next("[a-zA-Z]+"));
        System.out.println("Write last name:");
        student.setLastName(scanner.next("[a-zA-Z]+"));
        System.out.println("Write group name from this list:");
        System.out.println(groupService.findAll());
        String groupName = scanner.next("[a-zA-Z]{2}-\\\\d{2}");
        try {
            student.setGroup(groupService.getByName(groupName));
        } catch (Exception e) {
            logger.info("Group with this name does not exist");
        }
        studentService.add(student);
    }

    private void findAllStudentsByGroup() {
        System.out.println("Enter the group name");
        System.out.println(groupService.findAll());
        String groupName = scanner.next("[a-zA-Z]{2}-\\\\d{2}");
        System.out.println("All students from this group:\n"
                + studentService.findAllStudentsByGroup(groupName));

    }

    private void findAllGroupsWithLessOrEqualStudentsNumber() {
        System.out.println("Enter the maximum number of students:");
        int maxStudents = scanner.nextInt();
        System.out.println("Groups with less or equal students’ number:\n"
                + groupService.findByStudentCountLessThanEqual(maxStudents));
    }
}
