package taskmanager.task.auth.dto;

import lombok.Data;

@Data
public class SignUpRequest {

    private String username;
    private String password;
}
