package com.luoguohua.finance.admin.service;

import com.luoguohua.finance.admin.repository.SysUserRepository;
import com.luoguohua.finance.admin.domain.po.SysUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/5/11 17:01
 * Content:
 */
@Service
@AllArgsConstructor
public class SysUserService {

    private final SysUserRepository userRepository;

    public List<SysUser> getAllSysUsers() {
        return userRepository.findAll();
    }

    public Optional<SysUser> getSysUserById(Long id) {
        return userRepository.findById(id);
    }

    public SysUser createSysUser(SysUser user) {
        return userRepository.save(user);
    }

    public SysUser updateSysUser(SysUser userDetails) {
        SysUser user = userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new RuntimeException("SysUser not found with id: " + userDetails.getId()));
        // 更新其他属性
        user.setUsername(userDetails.getUsername());
        user.setPasswd(userDetails.getPasswd());
        return userRepository.save(user);
    }

    public void deleteSysUser(Long id) {
        SysUser user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("SysUser not found with id: " + id));
        userRepository.delete(user);
    }
}
