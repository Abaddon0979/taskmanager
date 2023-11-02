import org.junit.jupiter.api.Test;
import taskmanager.task.api.exceptions.TaskNotFoundException;
import taskmanager.task.api.model.Task;
import taskmanager.task.api.service.TaskService;
import taskmanager.task.api.service.TaskServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    @Test
    void addTaskTest() {
        TaskService taskService = new TaskServiceImpl();
        Task task = new Task(1,"Test1","TestDesc1", LocalDate.now(),false);

        taskService.addTask(task);

        ArrayList<Task> allTasks = taskService.getAllTasks();
        assertEquals(1, allTasks.size());
        assertEquals(task, allTasks.get(0));
    }

    @Test
    void getAllTasksTest() {
        TaskService taskService = new TaskServiceImpl();
        Task task1 = new Task(1,"Test1","TestDesc1", LocalDate.now(),false);
        Task task2 = new Task(2,"Test2","TestDesc2", LocalDate.now(),false);
        Task task3 = new Task(3,"Test3","TestDesc3", LocalDate.now(),false);

        taskService.addTask(task1);
        taskService.addTask(task2);
        taskService.addTask(task3);

        ArrayList<Task> allTasks = taskService.getAllTasks();
        assertEquals(3, allTasks.size());
        assertTrue(allTasks.contains(task1));
        assertTrue(allTasks.contains(task2));
        assertTrue(allTasks.contains(task3));
    }

    @Test
    void getTaskByIDTest() throws TaskNotFoundException {
        TaskService taskService = new TaskServiceImpl();
        Task task = new Task(1,"Test1","TestDesc1", LocalDate.now(),false);

        taskService.addTask(task);

        Task resultTask = taskService.getTaskByID(1);
        assertNotNull(resultTask);
        assertEquals(task, resultTask);
    }

    @Test
    void setTaskAsDoneTest() throws TaskNotFoundException {
        TaskService taskService = new TaskServiceImpl();
        Task task = new Task(1,"Test1","TestDesc1", LocalDate.now(),false);

        taskService.addTask(task);

        taskService.setTaskAsDone(1);

        assertTrue(task.isDone());
    }

    @Test
    void setTaskAsNotDoneTest() throws TaskNotFoundException {
        TaskService taskService = new TaskServiceImpl();
        Task task = new Task(1,"Test1","TestDesc1", LocalDate.now(),true);

        taskService.addTask(task);

        taskService.setTaskAsNotDone(1);

        assertFalse(task.isDone());
    }

    @Test
    void modifyTaskTest() throws TaskNotFoundException {
        TaskService taskService = new TaskServiceImpl();
        Task task = new Task(1,"Test1","TestDesc1", LocalDate.now(),false);

        taskService.addTask(task);

        Task newTask = new Task (1,"Test2","TestDesc2", LocalDate.now(),true);
        taskService.modifyTask(1, newTask);

        Task resultTask = taskService.getTaskByID(1);
        assertNotNull(resultTask);
        assertEquals(newTask, resultTask);
    }

    @Test
    void deleteTaskTest() throws TaskNotFoundException {
        TaskService taskService = new TaskServiceImpl();
        Task task = new Task(1,"Test1","TestDesc1", LocalDate.now(),false);

        taskService.addTask(task);

        taskService.deleteTask(1);
        ArrayList<Task> allTasks = taskService.getAllTasks();
        assertTrue(allTasks.isEmpty());
    }
}
