package com.nms.uoc.config.security.filter;

import com.nms.uoc.config.security.model.SysUserDetails;
import com.nms.uoc.contain.DELETED;
import com.nms.uoc.service.impl.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserEntityService userEntityService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        SysUserDetails sysUserDetails = (SysUserDetails) userEntityService.loadUserByUsername(username);
        if (sysUserDetails == null) {
            throw new BadCredentialsException("Username is not valid");
        }else if (!new BCryptPasswordEncoder().matches(password, sysUserDetails.getPassword())) {
            throw new BadCredentialsException("Password wrong");
        }else if (sysUserDetails.getStatus().equals(DELETED.TRUE)) {
            throw new LockedException("Account is deleted");
        }
        System.out.println("UserAuthenticationProvider 79: " + sysUserDetails.getAuthorities());
        return new UsernamePasswordAuthenticationToken(sysUserDetails, password, sysUserDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
