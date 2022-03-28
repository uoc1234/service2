package com.nms.uoc.service.impl;

import com.nms.uoc.config.security.model.SysUserDetails;
import com.nms.uoc.contain.DELETED;
import com.nms.uoc.contain.STATUS;
import com.nms.uoc.model.entity.RoleTable;
import com.nms.uoc.model.entity.UserEntity;
import com.nms.uoc.repository.RoleTableRepository;
import com.nms.uoc.repository.UserRepository;
import com.nms.uoc.utils.PasswordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserEntityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleTableRepository roleTableRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        SysUserDetails sysUserDetails = new SysUserDetails();

        if (user.isPresent() && user.get().getStatus() == STATUS.ACTIVE) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            BeanUtils.copyProperties(user.get(), sysUserDetails);

            List<String> permissionList = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.get().getRole().getName()));

            permissionList.addAll(userRepository.listPermissionByRole(user.get().getRole().getId()));

            permissionList.forEach(permission -> {
                authorities.add(new SimpleGrantedAuthority("PERMISSION_" + permission));
            });

            sysUserDetails.setAuthorities(authorities);
            return sysUserDetails;
        } else {
            return null;
        }
    }


    public void init() {
        RoleTable roleTable = new RoleTable();
        roleTable.setName("ADMIN");
        roleTable.setStatus(STATUS.ACTIVE);
        roleTable.setDeleted(DELETED.FALSE);
        roleTableRepository.save(roleTable);


        UserEntity userEntity = new UserEntity();
        userEntity.check();
        userEntity.setEmail("Vanuoc9xhy@gmail.com");
        userEntity.setUsername("admin");
        userEntity.setPassword(PasswordUtils.encodeString("admin"));
        userEntity.setPhoneNumber("0377106297");
        userEntity.setRole(roleTable);
        userRepository.save(userEntity);

    }
}
