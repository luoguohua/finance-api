package com.luoguohua.finance.framework.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luoguohua.finance.admin.domain.po.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/7/9 11:48
 * Content:
 */
public class LoginUser implements UserDetails {


    private SysUser user;
    /**
     * 权限列表
     */
    private Set<String> permissions;


    public LoginUser()
    {
    }

    public LoginUser(SysUser user)
    {
        this.user = user;
    }

    public LoginUser(SysUser user, Set<String> permissions)
    {
        this.user = user;
        this.permissions = permissions;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Long getUserId(){
        return user.getId();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPasswd();
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
