package com.schoolManagement.LoginSystem.aop;

import com.schoolManagement.LoginSystem.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ErrorAttributeAspect {

    @Pointcut("execution(* com.schoolManagement.LoginSystem.controller..*(..)) && args(.., request)")
    public void requestMethods(HttpServletRequest request) {}

    @Before("requestMethods(request)")
    public void checkErrorAttribute(HttpServletRequest request) {
        Object error = request.getAttribute("error");
        if (error == null) {
            throw new AuthException("Error attribute is null");
        }
    }
}
