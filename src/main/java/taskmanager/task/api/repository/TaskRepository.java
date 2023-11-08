package taskmanager.task.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskmanager.task.api.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}