package com.example.MyRestTemplateApp.controller;

import com.example.MyRestTemplateApp.service.UserOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserOperationService userOperationService;

    @Autowired
    public UserController(UserOperationService userOperationService) {
        this.userOperationService = userOperationService;
    }

    @GetMapping("/perform-operations")
    public String performOperations() {
        return userOperationService.performOperation();
    }
}






























