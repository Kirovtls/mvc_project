package peaksoft.dao;

import peaksoft.entities.Course;
import peaksoft.entities.Lesson;
import peaksoft.entities.Student;

import java.util.List;

public interface LessonDao {
    List<Lesson> getAllLessons();
    void addLesson(Long id, Lesson lesson);
    Lesson getLessonById(Long id);
    void updateLesson(Lesson lesson, Long id);
    void deleteLesson(Long id);
}
