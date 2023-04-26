package com.example.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
@Order(1)
public class LoggingAspect {
    private static Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("execution(* com.example.springaop.controller.Controller.sayHello())")
    private void forHello(){}

    @Before("forHello()")
    public void beforeSayHello(JoinPoint joinPoint) {
        logger.info("Before advice for method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "forHello()", returning = "result")
    public void afterSayHello(JoinPoint joinPoint, String result) {
        logger.info("After advice for method: " + joinPoint.getSignature().getName());
        logger.info("Got result: " + result);
    }

    @AfterThrowing(pointcut = "forHello()", throwing = "throwable")
    public void afterSayHelloThrowing(JoinPoint joinPoint, Throwable throwable) {
        logger.severe("Method throwing exception: " + joinPoint.getSignature().getName());
        logger.severe("The exception: " + throwable);
    }

    // `@After` executes finally, regardless of an exception
    @Around("forHello()")
    public Object aroundSayHello(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("Around advice for method: " + proceedingJoinPoint.getSignature().getName());

        long begin = System.currentTimeMillis();

        Object result;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e){
            logger.info("Rethrow error: " + e);
            throw e;
        }

        long end = System.currentTimeMillis();

        logger.info("Duration: " + (end - begin) + "milliseconds");

        return result;
    }
}