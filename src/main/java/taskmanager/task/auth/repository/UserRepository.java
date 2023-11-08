package taskmanager.task.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskmanager.task.auth.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}