package taskmanager.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class TaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
		TaskManager taskManager = new TaskManager();
		Task task1 = new Task(01, "Task1", "Desc1", LocalDate.now(), false);
		Task task2 = new Task(02, "Task2", "Desc2", LocalDate.of(2023, 02, 25), false);
		taskManager.addTask(task1);
		taskManager.addTask(task2);
		taskManager.moveTaskToDone(task1);
		taskManager.printTasks();
	}
}
