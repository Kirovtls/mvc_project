package peaksoft.dao;

import peaksoft.entities.Lesson;
import peaksoft.entities.Task;

import java.util.List;

public interface TaskDao {
    List<Task> getAllTasks();
    void addTask(Long id, Task task);
    Task getTaskById(Long id);
    void updateTask(Task task, Long id);
    void deleteTask(Long id);
}
