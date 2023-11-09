package taskmanager.task.auth.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import taskmanager.task.auth.repository.UserRepository;
import taskmanager.task.auth.services.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

@Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
            }
        };
    }
}
