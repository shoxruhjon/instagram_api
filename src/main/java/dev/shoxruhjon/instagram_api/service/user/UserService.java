package dev.shoxruhjon.instagram_api.service.user;

import dev.shoxruhjon.instagram_api.dto.request.LoginRequest;
import dev.shoxruhjon.instagram_api.dto.request.UserCreateDto;
import dev.shoxruhjon.instagram_api.dto.response.AuthResponse;
import dev.shoxruhjon.instagram_api.dto.response.UserResponse;
import dev.shoxruhjon.instagram_api.entity.UserEntity;

import java.util.List;

public interface UserService {

    public AuthResponse registerUser(UserCreateDto user);
    public AuthResponse signin(LoginRequest loginRequest);
    public UserEntity findUserById(Integer userId);
    public UserEntity findUserByEmail(String email);
    public List<UserEntity> findAllUsers();
    public UserEntity followUser(Integer userId, Integer userId2);
    public UserEntity updateUser(UserEntity user, Integer userId);
    public List<UserEntity> searchUser(String query);
    public UserResponse findUserByJwt(String jwt);
}
