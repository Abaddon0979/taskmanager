package taskmanager.task.api.service;

import org.springframework.stereotype.Service;
import taskmanager.task.api.exceptions.TaskNotFoundException;
import taskmanager.task.api.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskByID(int taskID) throws TaskNotFoundException;
    void addTask (Task task);
    void modifyTitle (int taskID, String newTitle);
    void modifyDescription (int taskID, String newDescription);
    void modifyDueDate(int taskID, LocalDate dueDate);
    void deleteTask (int taskID) throws TaskNotFoundException;
    boolean setTaskAsDone(int taskID) throws TaskNotFoundException;
    boolean setTaskAsNotDone(int taskID) throws TaskNotFoundException;
}
