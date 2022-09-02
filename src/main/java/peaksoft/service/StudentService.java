package peaksoft.service;

import peaksoft.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents(Long courseId);
    void addStudent(Long id,Student student);
    Student getStudentById(Long id);
    void updateStudent(Student student, Long id);
    void deleteStudent(Long id);
    void assignStudent(Long courseId, Long studentId);
}