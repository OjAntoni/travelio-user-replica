package com.example.traveliouserreplica.model;

import com.example.traveliouserreplica.model.Provider;
import com.example.traveliouserreplica.model.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users_replica",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    @Column(name = "image-name")
    private String imgName;
    private boolean emailConfirmed;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(  name = "user_roles_replica",
            joinColumns = @JoinColumn(name = "user_id_replica"),
            inverseJoinColumns = @JoinColumn(name = "role_id_replica"))
    private Set<Role> roles = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private Provider provider;

    public User(String firstname, String lastname, String username, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
