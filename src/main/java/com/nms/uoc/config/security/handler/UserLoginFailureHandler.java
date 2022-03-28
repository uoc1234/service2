package com.nms.uoc.config.security.handler;

import com.nms.uoc.config.security.exeption.ResponseAuthentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) {
        ResponseAuthentication.responseJson(response, new ResponseAuthentication(401, exception.getMessage(), request.getRequestURI()));
    }
}
