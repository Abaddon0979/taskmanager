package taskmanager.task.auth.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import taskmanager.task.auth.dto.JWTAuthResponse;
import taskmanager.task.auth.dto.RefreshTokenRequest;
import taskmanager.task.auth.dto.SignInRequest;
import taskmanager.task.auth.dto.SignUpRequest;
import taskmanager.task.auth.entities.Role;
import taskmanager.task.auth.entities.User;
import taskmanager.task.auth.repository.UserRepository;
import taskmanager.task.auth.services.AuthService;
import taskmanager.task.auth.services.JWTService;

import java.util.HashMap;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public User signup(SignUpRequest signUpRequest){
        User user = new User();

        user.setUsername(signUpRequest.getUsername());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(user);
    }

    public JWTAuthResponse signIn(SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getUsername(), signInRequest.getPassword()));

        var user = userRepository.findByUsername(signInRequest.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password!"));

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();

        jwtAuthResponse.setToken(jwt);
        jwtAuthResponse.setRefreshToken(refreshToken);
        return jwtAuthResponse;
    }

    public JWTAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String username = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByUsername(username).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwt = jwtService.generateToken(user);

            JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();

            jwtAuthResponse.setToken(jwt);
            jwtAuthResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthResponse;
        }
        return null;
    }
}
