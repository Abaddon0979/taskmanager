package taskmanager.task;
import java.util.ArrayList;
public class TaskManager {
    private ArrayList<Task> toDoTasks = new ArrayList<>();
    private ArrayList<Task> doneTasks = new ArrayList<>();

    public void addTask(Task task) {
        toDoTasks.add(task);
    }

    public void moveTaskToDone(Task task) {
        toDoTasks.remove(task);
        task.setAsDone();
        doneTasks.add(task);
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
