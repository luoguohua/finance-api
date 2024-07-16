package com.luoguohua.finance.framework.controller;

import com.luoguohua.finance.common.domain.LoginBody;
import com.luoguohua.finance.common.domain.R;
import com.luoguohua.finance.framework.manager.LoginManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/7/9 14:38
 * Content:
 */
@Tag(name = "用户登录",description = "用户登录")
@RestController
public class LoginController {

    @Autowired
    private LoginManager loginManager;

    @Operation(summary = "用户登录", description = "用户登录")
    @SecurityRequirements()
    @PostMapping("login")
    public R<String> login(@RequestBody LoginBody body){
        return R.success(loginManager.login(body));
    }


}
