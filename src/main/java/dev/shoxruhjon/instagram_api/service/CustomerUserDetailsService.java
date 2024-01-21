package dev.shoxruhjon.instagram_api.service;

import dev.shoxruhjon.instagram_api.entity.UserEntity;
import dev.shoxruhjon.instagram_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email + " + username));
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
}
