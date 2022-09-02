package peaksoft.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entities.Company;

import peaksoft.entities.Course;
import peaksoft.entities.Instructor;
import peaksoft.entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Student> getAllStudents(Long id) {
        List<Student> students = manager.createQuery("select s from Student s", Student.class).getResultList();
        return students;
    }

    @Override
    public void addStudent(Long id, Student student) {
        Company company = manager.find(Company.class, id);
        company.addStudent(student);
        student.setCompany(company);
        manager.merge(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return manager.find(Student.class, id);
    }

    @Override
    public void updateStudent(Student student, Long id) {
        Student student1 = manager.find(Student.class, id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        manager.merge(student1);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = manager.find(Student.class, id);
        student.setCourse(null);
        student.setCompany(null);
        manager.remove(student);
    }

    @Override
    public void assignStudent(Long courseId, Long studentId) {
        Student student = manager.find(Student.class, studentId);
        Course course = manager.find(Course.class, courseId);
        course.addStudents(student);
        student.setCourse(course);
        manager.merge(student);
    }
}
