package taskmanager.task.api.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Task {
    private int taskID;
    private String title;
    private String description;
    private LocalDate taskDate;
    private boolean done;

    public Task (int taskID, String title, String description, LocalDate taskDate, boolean done) {
        this.taskID = taskID;
        this.title = title;
        this.description = description;
        this.taskDate = taskDate;
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

    public LocalDate getTaskDate() {
        return taskDate;
    }

    public void setTaskDate (LocalDate taskDate) {
        this.taskDate = taskDate;
    }

    public boolean isDone() {
        return done;
    }

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
