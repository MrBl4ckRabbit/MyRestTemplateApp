package com.example.MyRestTemplateApp.service;

import com.example.MyRestTemplateApp.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final RestTemplate restTemplate;
    private String sessionId;


    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    public List<User> getAllUsers() {
        String url = "http://94.198.50.185:7081/api/users";
        ResponseEntity<User[]> response = restTemplate.getForEntity(url, User[].class);
        HttpHeaders headers = response.getHeaders();
        sessionId = headers.getFirst(HttpHeaders.SET_COOKIE);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public String saveUser(User user) {
        String url = "http://94.198.50.185:7081/api/users";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.COOKIE, sessionId);
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return Objects.requireNonNull(response.getBody());
    }

    public String updateUser(User user) {
        String url = "http://94.198.50.185:7081/api/users";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.COOKIE, sessionId);
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
        return Objects.requireNonNull(response.getBody());
    }

    public String deleteUser(Long id) {
        String url = "http://94.198.50.185:7081/api/users/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.COOKIE, sessionId);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
        return Objects.requireNonNull(response.getBody());

    }
}