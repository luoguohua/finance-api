package com.luoguohua.finance.framework.generator;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/7/9 10:03
 * Content:
 */
@Slf4j
public class SnowIdGenerator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {

        return IdUtil.getSnowflake().nextId();
    }
}
