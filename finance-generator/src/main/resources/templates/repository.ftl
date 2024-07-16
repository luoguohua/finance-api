package ${packageName}.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ${packageName}.domain.po.${className};

/**
* @Version 1.0
* @Author: ${author}
* @Date: <#assign currentTime = .now?string("yyyy/MM/dd HH:mm")> ${currentTime}
* Content:
* ${tableRemarks} dao
*/

public interface ${className}Repository extends JpaRepository<${className}, Long> {
}