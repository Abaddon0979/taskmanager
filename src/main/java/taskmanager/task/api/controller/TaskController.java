package taskmanager.task.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import taskmanager.task.api.exceptions.TaskNotFoundException;
import taskmanager.task.api.model.Task;
import taskmanager.task.api.service.TaskService;
import java.util.ArrayList;

@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController (TaskService taskService){
        this.taskService = taskService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/alltasks")
    public ArrayList<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/task")
    public ResponseEntity<?> getTaskByID(@RequestParam int taskID) throws TaskNotFoundException {
        Task task = taskService.getTaskByID(taskID);
        if (task != null){
            return ResponseEntity.ok(task);
        }
        throw new TaskNotFoundException("Task " + taskID + " does not exist!");
    }

    @PostMapping("/addtask")
    public String addTask(@RequestBody Task task){
        taskService.addTask(task);
        return "New task added successfully!";
    }

    @PutMapping("/modify")
    public String modifyTask(@RequestParam int taskID, @RequestBody Task newTask){
        taskService.modifyTask(taskID, newTask);
        return "Task " + taskID + " modified successfully!";
    }

    @DeleteMapping("/delete")
    public String deleteTask(@RequestParam int taskID) throws TaskNotFoundException {
        Task task = taskService.getTaskByID(taskID);
        if (task != null){
            taskService.deleteTask(taskID);
            return "Task " + taskID + " deleted successfully!";
        }
        throw new TaskNotFoundException("Task " + taskID + " does not exist, it may have been already deleted or never existed!");
    }
}
