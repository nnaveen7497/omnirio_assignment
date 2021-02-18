package com.omnirio.userservice.resource;

import com.omnirio.userservice.model.User;
import com.omnirio.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserResource {

    private final UserService userService;

    UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUserList() {
        List<User> userList = userService.getUserList();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable("userId") String userId) {
        User user = userService.getUserDetails(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping
    public ResponseEntity<User> saveUserDetails(@RequestBody User user) {
        User savedUser = userService.saveUserDetails(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}
