-- Insert a single student
INSERT INTO Department (deptname) VALUES ('cse');
INSERT INTO Department (deptname) VALUES ('Ece');

INSERT INTO Student (name, course, phone, email,dept_Id)
VALUES ('Murali Krishna', 'python', '9876543210', 'murali@example.com',1);

-- Insert multiple students in one statement
INSERT INTO Student (name, course, phone, email,dept_Id)
VALUES 
('Anusha Reddy', 'c++', '9123456780', 'anusha@example.com',2),
('Ravi Teja', '#c', '9988776655', 'ravi@example.com',1),
('Sita Devi', 'java script', '9345678901', 'sita@example.com',2);

-- Another single insert
INSERT INTO Student (name, course, phone, email,dept_Id)
VALUES ('Kiran Kumar', 'aws', '9001122334', 'kiran@example.com',1);
-- USERS TABLE DATA
-- USERS TABLE
