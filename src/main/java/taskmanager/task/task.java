package taskmanager.task;

import java.time.LocalDate;

public class task {
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
        this.done = false;
    }

    public int getID() {
        return taskID;
    }

    public void setID(String taskID) {
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
}
