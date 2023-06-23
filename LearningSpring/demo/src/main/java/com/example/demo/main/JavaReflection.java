package com.example.demo.main;

import com.example.demo.dto.Employee;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
public class JavaReflection {
  public static void main(String[] args) throws Exception {
      Class c = Class.forName("com.example.demo.main.Test");
      Test t = (Test) c.newInstance();
      Method m = c.getDeclaredMethod("show", null);
      m.setAccessible(true);
      m.invoke(t, null);

  }
}
@Slf4j
 class Test {
  private void show(){
    log.info(" Test java reflection !! ");
  }
 }