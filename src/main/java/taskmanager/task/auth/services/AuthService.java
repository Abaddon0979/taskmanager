package taskmanager.task.auth.services;

import taskmanager.task.auth.dto.JWTAuthResponse;
import taskmanager.task.auth.dto.RefreshTokenRequest;
import taskmanager.task.auth.dto.SignInRequest;
import taskmanager.task.auth.dto.SignUpRequest;
import taskmanager.task.auth.entities.User;

public interface AuthService {

    User signup(SignUpRequest signUpRequest);
    JWTAuthResponse signIn(SignInRequest signInRequest);
    JWTAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
