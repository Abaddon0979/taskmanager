package taskmanager.task.auth.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskmanager.task.auth.dto.JWTAuthResponse;
import taskmanager.task.auth.dto.RefreshTokenRequest;
import taskmanager.task.auth.dto.SignInRequest;
import taskmanager.task.auth.dto.SignUpRequest;
import taskmanager.task.auth.entities.User;
import taskmanager.task.auth.services.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authService.signup(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JWTAuthResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }
}
