-- Insert test data into groups table
INSERT INTO groups (group_name) VALUES
    ('Group A'),
    ('Group B'),
    ('Group C');

-- Insert test data into students table
INSERT INTO students (group_id, first_name, last_name) VALUES
    (1, 'John', 'Doe'),
    (1, 'Jane', 'Smith'),
    (2, 'Michael', 'Johnson'),
    (2, 'Emily', 'Brown'),
    (3, 'David', 'Lee');

-- Insert test data into courses table
INSERT INTO courses (course_name, course_description) VALUES
    ('Mathematics', 'Basic mathematics course'),
    ('History', 'Introduction to world history'),
    ('Science', 'General science course');

-- Insert test data into student_courses table (enrollments)
INSERT INTO student_courses (student_id, course_id) VALUES
    (1, 1), -- John Doe enrolled in Mathematics
    (1, 2), -- John Doe enrolled in History
    (2, 1), -- Jane Smith enrolled in Mathematics
    (3, 2), -- Michael Johnson enrolled in History
    (4, 3), -- Emily Brown enrolled in Science
    (5, 1), -- David Lee enrolled in Mathematics
    (5, 3); -- David Lee enrolled in Science
