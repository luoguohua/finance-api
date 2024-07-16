package E:\genrater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import E:\genrater.domain.po.SysUser;

/**
* @Version 1.0
* @Author: luoguohua
* @Date: 2024年7月16日 10:27:47
* Content:
* 用户信息 dao
*/

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
}