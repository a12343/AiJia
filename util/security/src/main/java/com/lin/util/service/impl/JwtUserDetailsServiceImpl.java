package com.lin.util.service.impl;


import com.lin.util.entity.JwtUser;
import com.lin.util.entity.User;
import com.lin.util.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * security用户认证实现类
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findByUsername(userName);

        if (user == null){
            throw new UsernameNotFoundException("未查询到用户名为 : " + userName);
        }

        return new JwtUser(user);
    }
}
