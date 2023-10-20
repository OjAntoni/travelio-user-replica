package com.example.traveliouserreplica.service;

import com.example.traveliouserreplica.dto.UserSearchCriteria;
import com.example.traveliouserreplica.model.User;
import com.example.traveliouserreplica.repository.UserRepository;
import com.fasterxml.jackson.databind.util.ArrayBuilders;

import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> searchUsers(UserSearchCriteria criteria) {
        return userRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.getFirstname() != null) {
                predicates.add(cb.like(root.get("firstname"), "%"+criteria.getFirstname()+"%"));
            }
            if (criteria.getLastname() != null) {
                predicates.add(cb.like(root.get("lastname"), "%"+criteria.getLastname()+"%"));
            }
            if (criteria.getUsername() != null) {
                predicates.add(cb.like(root.get("username"), "%"+criteria.getUsername()+"%"));
            }
            if (criteria.getEmail() != null) {
                predicates.add(cb.equal(root.get("email"), "%"+criteria.getEmail()+"%"));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
