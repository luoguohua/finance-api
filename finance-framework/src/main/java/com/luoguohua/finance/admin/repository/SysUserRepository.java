package com.luoguohua.finance.admin.repository;

import com.luoguohua.finance.admin.domain.po.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/5/11 17:00
 * Content:
 */
public interface SysUserRepository extends JpaRepository<SysUser,Long> {
}
