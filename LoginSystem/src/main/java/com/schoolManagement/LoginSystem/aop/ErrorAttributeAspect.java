package com.schoolManagement.LoginSystem.aop;

import com.schoolManagement.LoginSystem.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/*

@Aspect
@Component
public class ErrorAttributeAspect {

    @Pointcut("execution(* com.schoolManagement.LoginSystem.controller..*(..)) && args(.., request)")
    public void requestMethods(HttpServletRequest request) {}

    @Before("requestMethods(request)")
    public void checkErrorAttribute(HttpServletRequest request) {
        Object error = request.getAttribute("error");
        if (error != null) {
            throw new AuthException("Error attribute is null");
        }
    }
}
*/
@Aspect
@Component
public class ErrorAttributeAspect {

    // Pointcut to match all controller methods returning ResponseEntity, regardless of parameters
   /* @Pointcut("execution(org.springframework.http.ResponseEntity *(..))")
    public void controllerMethods() {}

    // Before advice that checks for any Exception argument in the method
    @Before("controllerMethods()")
    public Object checkForException(JoinPoint joinPoint)  {
        // Iterate over the method arguments to find Exception
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof Exception exception) {
                // Perform custom logic when an Exception is found
                return new AuthException("Exception detected: " + exception.getMessage());            }
        }
        return  null;
    }*/
}

