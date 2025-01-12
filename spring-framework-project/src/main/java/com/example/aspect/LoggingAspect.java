package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect       // Use only when using annotation based bean defining in AppConfig.java
@Component    // Use only when using annotation based bean defining in AppConfig.java
public class LoggingAspect {

/*

    // Use only when using annotation based bean defining in AppConfig.java
    // Before advice: Log method name before execution
    public void logBeforeMethod(JoinPoint joinPoint) {
        System.out.println("Before method execution: " + joinPoint.getSignature().getName());
    }

    // After advice: Log method name after execution
    public void logAfterMethod(JoinPoint joinPoint) {
        System.out.println("After method execution: " + joinPoint.getSignature().getName());
    }

    // After-returning advice: Log method name and result after method execution
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("Method executed successfully: " + joinPoint.getSignature().getName());
        System.out.println("Returned value: " + result);
    }
*/

    // Before advice: Log method name before execution
    @Before("execution(* com.example.service.EmployeeServiceImpl.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        System.out.println("Before method execution: " + joinPoint.getSignature().getName());
    }

    // After advice: Log method name after execution
    @After("execution(* com.example.service.EmployeeServiceImpl.*(..))")
    public void logAfterMethod(JoinPoint joinPoint) {
        System.out.println("After method execution: " + joinPoint.getSignature().getName());
    }

    // After-returning advice: Log method name and result after method execution
    @AfterReturning(pointcut = "execution(* com.example.service.EmployeeServiceImpl.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("Method executed successfully: " + joinPoint.getSignature().getName());
        System.out.println("Returned value: " + result);
    }

}
