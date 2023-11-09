package taskmanager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import taskmanager.task.auth.entities.Role;
import taskmanager.task.auth.entities.User;
import taskmanager.task.auth.repository.UserRepository;

@SpringBootApplication
public class TaskApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

	public void run (String... args){
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if(adminAccount == null){
			User user = new User();
			user.setUsername("admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}
}
