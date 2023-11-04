package taskmanager.task.api.service;

import org.springframework.stereotype.Service;
import taskmanager.task.api.exceptions.TaskNotFoundException;
import taskmanager.task.api.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public interface TaskService {
    ArrayList<Task> getAllTasks();
    Task getTaskByID(int taskID) throws TaskNotFoundException;
    void addTask (Task task);
    void modifyTitle (int taskID, String newTitle);
    void modifyDescription (int taskID, String newDescription);
    void deleteTask (int taskID) throws TaskNotFoundException;
    boolean setTaskAsDone(Task task) throws TaskNotFoundException;
    boolean setTaskAsNotDone(Task task) throws TaskNotFoundException;
}
