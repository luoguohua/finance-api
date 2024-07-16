package E:\genrater.service;

import E:\genrater.domain.po.SysUser;
import E:\genrater.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Version 1.0
* @Author: luoguohua
* @Date: 2024年7月16日 10:27:47
* Content:
* 用户信息 service
*/

@Service
@AllArgsConstructor
public class SysUserService {

    @Autowired
    private SysUserRepository repository;

    public List<SysUser> findAll() {
        return repository.findAll();
    }

    public SysUser save(SysUser entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
