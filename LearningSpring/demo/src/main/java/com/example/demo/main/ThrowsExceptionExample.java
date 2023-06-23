package com.example.demo.main;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Slf4j
public class ThrowsExceptionExample {
    public static void main(String[] args) throws FileNotFoundException {
        checkedExceptionWithTryCatch();
    }
    private static void checkedExceptionWithTryCatch() throws FileNotFoundException {
        // Checked Exception
        // when try to catch and throw new Checked Exception :
        // 1. if you catch any checked exception,
        // Java will show error message like is never occur by this checked exception
        // So, should use Exception to catch
        // 2. if throw new checked exception,
        // Java will mandatory try/catch or throws because it must compile at compile time

        String fileName = "not_existing_file.txt";
        File file = new File(fileName);
        try {
            FileInputStream stream = new FileInputStream(file);
//            Integer a = null;
//            log.info(" x/2 ={}", a%2);
        } catch (Exception e) {
            log.info(" exception in catch");
//            try {
                throw new FileNotFoundException(String.format(" exception ne %s", e));
//            } catch (FileNotFoundException ex) {
//                ex.printStackTrace();
//            }
//            throw new NullPointerException("hai null");
        }

        //Unchecked Exception
//        Integer x = 1;
//        log.info(" x/2 ={}", x%0);
//        int arr[] = new int[5];// [0,0,0,0,0]
//        arr[5] = 4;
//        log.info("arr[5] = {}", arr[5]);
    }
}
