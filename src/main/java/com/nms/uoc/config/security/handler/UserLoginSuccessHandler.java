package com.nms.uoc.config.security.handler;

import com.nms.uoc.config.security.exeption.ResponseAuthentication;
import com.nms.uoc.config.security.model.SysUserDetails;
import com.nms.uoc.config.security.token.JWTConfig;
import com.nms.uoc.config.security.token.JWTTokenUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
        sysUserDetails.setIp(request.getHeader("User-Agent"));
        String token = JWTTokenUtils.createAccessToken(sysUserDetails);
        JWTTokenUtils.setTokenInfo(token, sysUserDetails.getUsername(), request.getHeader("User-Agent"));
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token.replace(JWTConfig.tokenPrefix, "").trim());
        ResponseAuthentication.responseSuccess(response, tokenMap);
    }
}
