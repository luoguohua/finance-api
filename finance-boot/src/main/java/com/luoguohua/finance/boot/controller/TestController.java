package com.luoguohua.finance.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/5/9 19:31
 * Content:
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String test(){
        return "hello world";
    }
}
