package taskmanager.task.api.service;

import org.springframework.stereotype.Service;
import taskmanager.task.api.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class TaskServiceImpl implements TaskService {

    private ArrayList<Task> toDoTasks;
    private ArrayList<Task> doneTasks;

    public TaskServiceImpl(){

        toDoTasks = new ArrayList<>();
        doneTasks = new ArrayList<>();

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
    public ArrayList<Task> getAllTasks(){
        ArrayList<Task> allTasks = new ArrayList<>();
        allTasks.addAll(toDoTasks);
        allTasks.addAll(doneTasks);
        return allTasks;
    }

    @Override
    public Task getTaskByID(int taskID) {
        for (Task task : toDoTasks){
            if (taskID == task.getID()){
                return task;
            }
        }

        for (Task task : doneTasks){
            if (taskID == task.getID()){
                return task;
            }
        }
        return null;
    }

    @Override
    public void addTask(Task task) {
        (task.isDone() ? doneTasks : toDoTasks).add(task);
    }

    @Override
    public void modifyTask(int taskID, Task newTask) {

        for (Task task : doneTasks) {
            if (taskID == task.getID()) {
                if (!task.isDone()) {
                    int index = toDoTasks.indexOf(task);
                    toDoTasks.set(index, newTask);
                } else {
                    toDoTasks.remove(task);
                    doneTasks.add(newTask);
                }
                return;
            }
        }

        for (Task task : toDoTasks) {
            if (taskID == task.getID()) {
                if (task.isDone()) {
                    int index = doneTasks.indexOf(task);
                    doneTasks.set(index, newTask);
                } else {
                    doneTasks.remove(task);
                    toDoTasks.add(newTask);
                }
                return;
            }
        }
    }

    @Override
    public void deleteTask (int taskID){
        Task taskToDelete = getTaskByID(taskID);
            if (toDoTasks.contains(taskToDelete)){
                toDoTasks.remove(taskToDelete);
            }

            if (doneTasks.contains(taskToDelete)){
                doneTasks.remove(taskToDelete);
            }
    }

    @Override
    public boolean setTaskAsDone(int taskID) {
        Task task = getTaskByID(taskID);
        if (toDoTasks.contains(task)){
            toDoTasks.remove(task);
            task.setAsDone();
            doneTasks.add(task);
            return true;
        }
        return false;
    }

    @Override
    public boolean setTaskAsNotDone(int taskID){
        Task task = getTaskByID(taskID);
        if (doneTasks.contains(task)) {
            doneTasks.remove(task);
            task.setAsDone();
            toDoTasks.add(task);
            return true;
        }
        return false;
    }

    public void printTasks() {
        System.out.println("To do:");
        for (Task task : toDoTasks) {
            System.out.println("ID: " + task.getID() + " Title: " + task.getTitle() + " Description: " + task.getDescription() + " Date: " + task.getTaskDate());
        }

        System.out.println("\nDone:");
        for (Task task : doneTasks) {
            System.out.println("ID: " + task.getID() + " Title: " + task.getTitle() + " Description: " + task.getDescription() + " Date: " + task.getTaskDate());
        }
    }
}
