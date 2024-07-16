package ${packageName}.service;

import ${packageName}.domain.po.${className};
import ${packageName}.repository.${className}Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Version 1.0
* @Author: ${author}
* @Date: <#assign currentTime = .now?string("yyyy/MM/dd HH:mm")> ${currentTime}
* Content:
* ${tableRemarks} service
*/

@Service
@AllArgsConstructor
public class ${className}Service {

    @Autowired
    private ${className}Repository repository;

    public List<${className}> findAll() {
        return repository.findAll();
    }

    public ${className} save(${className} entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
