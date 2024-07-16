package com.luoguohua.finance.framework.service;

import com.luoguohua.finance.admin.domain.po.SysUser;
import com.luoguohua.finance.admin.service.SysUserService;
import com.luoguohua.finance.framework.domain.dto.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/7/9 11:46
 * Content:
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.getUserByUsername(username);

        return createLoginUser(user);
    }

    /**
     * 创建系统用户
     * @param user
     * @return
     */
    public UserDetails createLoginUser(SysUser user)
    {
        return new LoginUser(user);
    }

}
