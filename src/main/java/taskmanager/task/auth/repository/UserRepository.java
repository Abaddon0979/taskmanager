package taskmanager.task.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taskmanager.task.auth.entities.Role;
import taskmanager.task.auth.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    User findByRole(Role role);
}