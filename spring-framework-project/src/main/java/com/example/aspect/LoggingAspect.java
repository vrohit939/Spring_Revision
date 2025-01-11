package com.example.aspect;

import org.aspectj.lang.JoinPoint;

public class LoggingAspect {

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

}
