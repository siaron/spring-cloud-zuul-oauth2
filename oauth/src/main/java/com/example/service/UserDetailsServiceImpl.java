package com.example.service;

import com.example.ErrorCode;
import com.example.ServiceException;
import com.example.entity.ActionEntity;
import com.common.dto.SecurityUser;
import com.example.entity.UserEntity;
import com.example.repository.ActionRepository;
import com.example.repository.UserRepository;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author Shenluw 创建日期：2018/3/22 16:14
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> grantedAuthorities = Lists.newArrayList();

        UserEntity user = userRepository.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        List<ActionEntity> actionEntities = actionRepository.findByUId(user.getId());

        List<GrantedAuthority> authList = Lists.newArrayList();
        for (ActionEntity actionEntity : actionEntities) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(actionEntity.getUrl());
            authList.add(grantedAuthority);
        }

        return new SecurityUser(user.getId(), user.getUserName(), user.getPassWord(),
                user.getUserName(), user.getOrgId(), user.getOrgName(), user.getStatus(), grantedAuthorities);
    }
}
