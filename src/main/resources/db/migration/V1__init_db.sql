-- Create the groups table
CREATE TABLE groups (
    group_id LONG AUTO_INCREMENT PRIMARY KEY,
    group_name VARCHAR(100) NOT NULL
);

-- Create the students table
CREATE TABLE students (
    student_id LONG AUTO_INCREMENT PRIMARY KEY,
    group_id LONG,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    FOREIGN KEY (group_id) REFERENCES groups(group_id)
);

-- Create the courses table
CREATE TABLE courses (
    course_id LONG AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    course_description TEXT
);

-- Create the junction table for the many-to-many relationship
CREATE TABLE student_courses (
    student_id LONG,
    course_id LONG,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
);
