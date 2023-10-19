package com.example.traveliouserreplica.dto;

import com.example.traveliouserreplica.model.User;
import lombok.Data;

import java.net.URL;

@Data
public class UserProfileResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private URL imageUrl;

    public UserProfileResponse(User user) {
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
