package com.luoguohua.finance.framework.manager;

import com.luoguohua.finance.common.domain.LoginBody;
import com.luoguohua.finance.common.exception.BusinessException;
import com.luoguohua.finance.framework.component.JwtToken;
import com.luoguohua.finance.framework.domain.dto.LoginUser;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/7/9 14:44
 * Content:
 */
@Service
@AllArgsConstructor
public class LoginManager {

    private final AuthenticationManager authenticationManager;

    private final JwtToken jwtToken;


    public String login(LoginBody body){
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword()));
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new BusinessException("无效的用户名或密码");
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return jwtToken.generateToken(loginUser);
    }
}
