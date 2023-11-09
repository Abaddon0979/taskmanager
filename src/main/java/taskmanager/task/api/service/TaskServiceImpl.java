package taskmanager.task.api.service;

import org.springframework.stereotype.Service;
import taskmanager.task.api.model.Task;
import taskmanager.task.api.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository){

        this.taskRepository = taskRepository;

        Task task1 = new Task(1, "Title1","Desc1", LocalDate.of(2023, 2, 25),false);
        Task task2 = new Task(2, "Title2","Desc2", LocalDate.of(2023, 2, 26),false);
        Task task3 = new Task(3, "Title3","Desc3", LocalDate.of(2023, 2, 27),true);
        Task task4 = new Task(4, "Title4","Desc4", LocalDate.of(2023, 2, 28),false);
        Task task5 = new Task(5, "Title5","Desc5", LocalDate.of(2023, 3, 2),false);
        Task task6 = new Task(6, "Title6","Desc6", LocalDate.of(2023, 5, 8),false);
        Task task7 = new Task(7, "Title7","Desc7", LocalDate.of(2023, 6, 11),true);
        Task task8 = new Task(8, "Title8","Desc8", LocalDate.of(2023, 9, 23),false);
        Task task9 = new Task(9, "Title9","Desc9", LocalDate.of(2023, 10, 22),false);
        Task task10 = new Task(10, "Title10","Desc10", LocalDate.of(2023, 10, 31),true);

        addTask(task1);
        addTask(task2);
        addTask(task3);
    }
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskByID(int taskID) {
        return taskRepository.findById(taskID).orElse(null);
    }

    @Override
    public void addTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void modifyTitle(int taskID, String newTitle) {
        Task task = getTaskByID(taskID);
        if (task != null) {
            task.setTitle(newTitle);
            taskRepository.save(task);
        }
    }

    @Override
    public void modifyDescription(int taskID, String newDescription) {
        Task task = getTaskByID(taskID);
        if (task != null) {
            task.setDescription(newDescription);
            taskRepository.save(task);
        }
    }

    @Override
    public void modifyDueDate(int taskID, LocalDate newDueDate) {
        Task task = getTaskByID(taskID);
        if (task != null) {
            task.setDueDate(newDueDate);
            taskRepository.save(task);
        }
    }

    @Override
    public void deleteTask (int taskID){
        Task taskToDelete = getTaskByID(taskID);
        taskRepository.delete(taskToDelete);
    }

    @Override
    public boolean setTaskAsDone(int taskID){
        Task task = getTaskByID(taskID);

        if (!task.isDone()) {
            task.setDoneStatus(true);
            taskRepository.save(task);
            return true;
        }

        return false;
    }

    @Override
    public boolean setTaskAsNotDone(int taskID){
        Task task = getTaskByID(taskID);

        if (task.isDone()) {
            task.setDoneStatus(false);
            taskRepository.save(task);
            return true;
        }

        return false;
    }
}
