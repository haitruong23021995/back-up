package com.example.demo.main;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Slf4j
public class ThrowsExceptionExample2 {
    public static void main(String[] args) {
        checkedExceptionWithTryCatch();
    }
    private static void checkedExceptionWithTryCatch(){
        int a =5;
        try {
            log.info("{} ",a/0);
        } catch (Exception e) {
            log.info(" exception in catch");
            try {
                throw new FileNotFoundException(String.format(" exception ne %s", e));
            } catch (FileNotFoundException ex) {

            }
//            } catch (FileNotFoundException ex) {
//                ex.printStackTrace();
//            }
//            throw new NullPointerException("hai null");
        }
    }
}
