package com.example.traveliouserreplica.controller;

import com.example.traveliouserreplica.dto.UserProfileResponse;
import com.example.traveliouserreplica.dto.UserSearchCriteria;
import com.example.traveliouserreplica.service.UserService;
import com.example.traveliouserreplica.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/user-replica")
public class UserReadController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/search")
    public ResponseEntity<List<UserProfileResponse>> searchUsersJpaSpec(@RequestBody UserSearchCriteria criteria) {
        List<UserProfileResponse> users = userService.searchUsers(criteria).stream().map(u -> {
            try {
                return userMapper.map(u);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toList();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
