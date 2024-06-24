package com.example.demo.Service;

import com.example.demo.model.LoginHistory;
import com.example.demo.Repository.LoginHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class KafkaConsumerService {

    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    @KafkaListener(topics = "login-history", groupId = "login-history-group")
    public void consume(Map<String, String> message) {
        String username = message.get("username");

        LoginHistory loginHistory = new LoginHistory();
        loginHistory.setUsername(username);
        loginHistory.setTimestamp(LocalDateTime.now());

        loginHistoryRepository.save(loginHistory);
    }
}
