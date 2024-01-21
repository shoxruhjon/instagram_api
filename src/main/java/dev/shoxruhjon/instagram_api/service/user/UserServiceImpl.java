package dev.shoxruhjon.instagram_api.service.user;

import dev.shoxruhjon.instagram_api.config.JwtProvider;
import dev.shoxruhjon.instagram_api.dto.request.LoginRequest;
import dev.shoxruhjon.instagram_api.dto.request.UserCreateDto;
import dev.shoxruhjon.instagram_api.dto.response.AuthResponse;
import dev.shoxruhjon.instagram_api.dto.response.UserResponse;
import dev.shoxruhjon.instagram_api.entity.UserEntity;
import dev.shoxruhjon.instagram_api.repository.UserRepository;
import dev.shoxruhjon.instagram_api.service.CustomerUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerUserDetailsService customerUserDetails;
    private final ModelMapper modelMapper;

    @Override
    public AuthResponse registerUser(UserCreateDto userCreateDto) {
        if (userRepository.findUserByEmail(userCreateDto.getEmail()).isPresent())
            throw new RuntimeException("This email already used with another account");
        UserEntity userEntity = modelMapper.map(userCreateDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        UserEntity user = userRepository.save(userEntity);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        String token = JwtProvider.generateToken(authentication);
        return new AuthResponse(token, "Register Success");
    }

    @Override
    public AuthResponse signin(LoginRequest loginRequest) {
        Authentication authentication =
                authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);
        return new AuthResponse(token, "Login Success");
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customerUserDetails.loadUserByUsername(email);

        if(userDetails == null)
            throw new BadCredentialsException("Invalid username");
        if (!passwordEncoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException("Password not matched");
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public UserEntity findUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity followUser(Integer userId, Integer userId2) {
        UserEntity reqUser = findUserById(userId);
        UserEntity user2 = findUserById(userId2);
        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(user2.getId());

        userRepository.save(reqUser);
        userRepository.save(user2);

        return reqUser;
    }

    @Override
    public UserEntity updateUser(UserEntity user, Integer userId) {
        UserEntity userEntity1 = findUserById(userId);
        UserEntity userEntity = userRepository.findById(userEntity1.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getFirstName() != null)
            userEntity.setFirstName(user.getFirstName());
        if (user.getLastName() != null)
            userEntity.setLastName(user.getLastName());
        if (user.getEmail() != null)
            userEntity.setEmail(user.getEmail());
        return userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public UserResponse findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        UserEntity user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserResponse.class);
    }
}
