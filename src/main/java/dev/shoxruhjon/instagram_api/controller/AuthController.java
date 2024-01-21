package dev.shoxruhjon.instagram_api.controller;

import dev.shoxruhjon.instagram_api.dto.request.LoginRequest;
import dev.shoxruhjon.instagram_api.dto.request.UserCreateDto;
import dev.shoxruhjon.instagram_api.dto.response.AuthResponse;
import dev.shoxruhjon.instagram_api.dto.response.UserResponse;
import dev.shoxruhjon.instagram_api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUser(@RequestBody UserCreateDto dto){
        return new ResponseEntity<>(userService.registerUser(dto), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(userService.signin(loginRequest), HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getUserFromToken(@RequestHeader("Authorization") String jwt){
        return new ResponseEntity<>(userService.findUserByJwt(jwt), HttpStatus.OK);
    }
}
