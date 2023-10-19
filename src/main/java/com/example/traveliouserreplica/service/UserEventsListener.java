package com.example.traveliouserreplica.service;

import com.example.traveliouserreplica.model.User;
import com.example.traveliouserreplica.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.JMSException;
import lombok.SneakyThrows;
import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserEventsListener {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @JmsListener(destination = "users_events", containerFactory = "topicListenerFactory")
    public void receiveTopicMessage(Message message) throws JMSException {
//        System.out.println(message);
        String messageType = message.getStringProperty("messageType");
//        System.out.println(messageType);
        if ("USER_CREATED".equals(messageType)) {
            User newUser = objectMapper.readValue(message.getBody(String.class), User.class);
            userRepository.save(newUser);
        } else if ("USER_UPDATED".equals(messageType)) {
            User updatedUser = objectMapper.readValue(message.getBody(String.class), User.class);
            userRepository.save(updatedUser);
        } else if("USER_DELETED".equals(messageType)) {

        }
        // ... so on
    }
}
