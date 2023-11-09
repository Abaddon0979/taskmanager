package taskmanager.task.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
public class Task {
    @Id
    private int taskID;
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean done;

    public Task(){
    }
    public Task (int taskID, String title, String description, LocalDate dueDate, boolean done) {
        this.taskID = taskID;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.done = done;
    }

    public int getID() {
        return taskID;
    }

    public void setID(int taskID) {
        this.taskID = taskID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle (String title) {
        this.title=title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription (String description){
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate (LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDoneStatus(Boolean done) {this.done = done;}

    public void setAsDone(ArrayList<Task> toDoTasks, ArrayList<Task> doneTasks) {
        this.done = true;
        toDoTasks.remove(this);
        doneTasks.add(this);
    }

    public void setAsNotDone(ArrayList<Task> toDoTasks, ArrayList<Task> doneTasks) {
        this.done = false;
        doneTasks.remove(this);
        toDoTasks.add(this);
    }
}
