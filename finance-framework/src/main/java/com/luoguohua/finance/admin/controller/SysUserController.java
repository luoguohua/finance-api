package com.luoguohua.finance.admin.controller;

import com.luoguohua.finance.admin.domain.po.SysUser;
import com.luoguohua.finance.admin.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/5/11 17:05
 * Content:
 */
@Tag(name = "用户管理",description = "系统用户管理")
@RestController
@RequestMapping("/users")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/list")
    public List<SysUser> getAllSysUsers() {
        return sysUserService.getAllSysUsers();
    }

    @Operation(summary = "查询用户", description = "通过用户id查询用户")
    @GetMapping("/{id}")
    public SysUser getSysUserById(@Parameter(description = "用户id") @PathVariable Long id) {
        return sysUserService.getSysUserById(id)
                .orElseThrow(() -> new RuntimeException("SysUser not found with id: " + id));
    }

    @Operation(summary = "创建用户", description = "用于创建用户")
    @PostMapping
    public SysUser createSysUser(@RequestBody SysUser user) {
        return sysUserService.createSysUser(user);
    }

    @Operation(summary = "更新用户", description = "用于更新用户")
    @PostMapping("/update")
    public SysUser updateSysUser(@RequestBody SysUser userDetails) {
        return sysUserService.updateSysUser(userDetails);
    }

    @Operation(summary = "删除用户", description = "通过用户id删除用户")
    @GetMapping("/delete/{id}")
    public void deleteSysUser(@Parameter(description = "用户id") @PathVariable Long id) {
        sysUserService.deleteSysUser(id);
    }
}
