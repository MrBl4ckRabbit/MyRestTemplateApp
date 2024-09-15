package com.example.MyRestTemplateApp.service;

import com.example.MyRestTemplateApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserOperationService {
    private final UserService userService;

    @Autowired
    public UserOperationService(UserService userService) {
        this.userService = userService;
    }

    public String performOperation() {
        userService.getAllUsers();

        User newUser = new User(3L, "James", "Brown", (byte) 30);
        String part1 = userService.saveUser(newUser);

        User updateUser2 = new User(3L, "Thomas", "Shelby", (byte) 30);
        String part2 = userService.updateUser(updateUser2);

        String part3 = userService.deleteUser(3L);
        return part1 + part2 + part3;
    }
}
