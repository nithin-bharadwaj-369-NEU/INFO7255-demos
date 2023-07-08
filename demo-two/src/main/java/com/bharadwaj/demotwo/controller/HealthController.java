package com.bharadwaj.demotwo.controller;

import com.bharadwaj.demotwo.dto.Message;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<Message> healthStatus(){
        return ResponseEntity.status(200).body(new Message("App is Up and Running!!! "));
    }
}
