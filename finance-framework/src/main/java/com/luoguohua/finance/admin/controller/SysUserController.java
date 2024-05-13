package com.luoguohua.finance.admin.controller;

import com.luoguohua.finance.admin.domain.po.SysUser;
import com.luoguohua.finance.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/5/11 17:05
 * Content:
 */
@RestController
@RequestMapping("/users")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/list")
    public List<SysUser> getAllSysUsers() {
        return sysUserService.getAllSysUsers();
    }

    @GetMapping("/{id}")
    public SysUser getSysUserById(@PathVariable Long id) {
        return sysUserService.getSysUserById(id)
                .orElseThrow(() -> new RuntimeException("SysUser not found with id: " + id));
    }

    @PostMapping
    public SysUser createSysUser(@RequestBody SysUser user) {
        return sysUserService.createSysUser(user);
    }

    @PostMapping("/update")
    public SysUser updateSysUser(@RequestBody SysUser userDetails) {
        return sysUserService.updateSysUser(userDetails);
    }

    @GetMapping("/delete{id}")
    public void deleteSysUser(@PathVariable Long id) {
        sysUserService.deleteSysUser(id);
    }
}
