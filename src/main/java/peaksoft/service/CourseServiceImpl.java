package peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.CourseDao;
import peaksoft.entities.Course;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{
    private final CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Course> getAllCourses(Long id) {
        return courseDao.getAllCourses();
    }

    @Override
    public void addCourse(Course course, Long id) {
        courseDao.addCourse( id,course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseDao.getCourseById(id);
    }

    @Override
    public void updateCourse(Course course, Long id) {
        courseDao.updateCourse(course, id);
    }

    @Override
    public void deleteCourse(Long id) {
        courseDao.deleteCourse(id);
    }
}
