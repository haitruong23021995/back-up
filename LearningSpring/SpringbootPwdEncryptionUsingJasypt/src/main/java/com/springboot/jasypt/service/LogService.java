package com.springboot.jasypt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LogService {

    public void addLog(String message){
        log.info(message);
    }
}
