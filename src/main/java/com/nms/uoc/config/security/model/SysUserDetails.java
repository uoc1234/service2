package com.nms.uoc.config.security.model;

import com.nms.uoc.model.entity.UserEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SysUserDetails extends UserEntity implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;
    private Collection<GrantedAuthority> authorities;
    private boolean isAccountNonExpired = false;
    private boolean isAccountNonLocked = false;
    private boolean isCredentialsNonExpired = false;
    private boolean isEnabled = true;
    private String ip;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}
