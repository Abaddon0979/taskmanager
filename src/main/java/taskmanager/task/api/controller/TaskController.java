package taskmanager.task.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import taskmanager.task.api.model.Task;
import taskmanager.task.api.service.TaskService;
import java.util.ArrayList;

@RestController
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController (TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/alltasks")
    public ArrayList<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/task")
    public Task getTaskByID(@RequestParam int taskID){
        Task task = taskService.getTaskByID(taskID);
        if (task != null){
            return task;
        }
        return null;
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
    public String deleteTask(@RequestParam int taskID){
        taskService.deleteTask(taskID);
        return "Task " + taskID + " deleted successfully!";
    }
}
