package taskmanager.task.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskmanager.task.api.exceptions.TaskNotFoundException;
import taskmanager.task.api.model.Task;
import taskmanager.task.api.service.TaskService;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController (TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/api/all-tasks")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/api/task")
    public ResponseEntity<?> getTaskByID(@RequestParam int taskID) throws TaskNotFoundException {
        Task task = taskService.getTaskByID(taskID);
        if (task != null) {
            return ResponseEntity.ok(task);
        }
        throw new TaskNotFoundException("Task " + taskID + " does not exist!");
    }

    @GetMapping("/api/set-as-done")
    public String setTaskAsDone(@RequestParam int taskID) throws TaskNotFoundException {
        Task task = taskService.getTaskByID(taskID);
        if (task != null) {
            return taskService.setTaskAsDone(taskID) ? "Task " + taskID + " successfully marked as done!"
                    : "Task " + taskID + " is already marked as done!";
        }
        throw new TaskNotFoundException("Task " + taskID + " doesn't exist!");
    }

    @GetMapping("/api/set-as-not-done")
    public String setTaskAsNotDone(@RequestParam int taskID) throws TaskNotFoundException {
        Task task = taskService.getTaskByID(taskID);
        if (task != null) {
            return taskService.setTaskAsNotDone(taskID) ? "Task " + taskID + " successfully marked as not done!"
                    : "Task " + taskID + " is already marked as not done!";
        }
        throw new TaskNotFoundException("Task " + taskID + " can't be marked as not done since it does not exist!");
    }

    @PostMapping("/api/add-task")
    public String addTask(@RequestBody Task task){
        taskService.addTask(task);
        return "New task added successfully!";
    }

    @PostMapping("/api/modify-title")
    public String modifyTitle(@RequestParam int taskID, @RequestBody String newTitle) throws TaskNotFoundException {
        Task task = taskService.getTaskByID(taskID);
        if (task != null) {
            taskService.modifyTitle(taskID, newTitle);
            return "Title of task " + taskID + " modified successfully!";
        }
        throw new TaskNotFoundException("Task " + taskID + " can't be modified, since it does not exist!");
    }

    @PostMapping("/api/modify-description")
    public String modifyDescription(@RequestParam int taskID, @RequestBody String newDescription) throws TaskNotFoundException {
        Task task = taskService.getTaskByID(taskID);
        if (task != null) {
            taskService.modifyTitle(taskID, newDescription);
            return "Title of task " + taskID + " modified successfully!";
        }
        throw new TaskNotFoundException("Task " + taskID + " can't be modified, since it does not exist!");
    }
    
    @DeleteMapping("/api/delete")
    public String deleteTask(@RequestParam int taskID) throws TaskNotFoundException {
        Task task = taskService.getTaskByID(taskID);
        if (task != null){
            taskService.deleteTask(taskID);
            return "Task " + taskID + " deleted successfully!";
        }
        throw new TaskNotFoundException("Task " + taskID + " does not exist, it may have been already deleted or never existed!");
    }
}
