package com.luoguohua.finance.framework.component;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.luoguohua.finance.framework.domain.dto.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/7/9 13:59
 * Content:
 *  Jwth
 */
@Component
public class JwtToken {


    @Autowired
    private RedisTemplate redisTemplate;

    private final String SECRET = "financeSecret";

    public final String CACHE_PREFIX = "token:";


    public String generateToken(LoginUser loginUser) {
        Map<String,Object> payload = new HashMap<>(8);
        payload.put("userId",loginUser.getUserId());
        payload.put("username",loginUser.getUsername());
        String token = JWTUtil.createToken(payload,SECRET.getBytes());
        redisTemplate.opsForValue().set(CACHE_PREFIX+token,loginUser, Duration.ofMinutes(30));
        return token;
    }

    public LoginUser extractLoginUser(String token){
        // 强转为LoginUser
        return (LoginUser) redisTemplate.opsForValue().get(CACHE_PREFIX+token);
    }

    @Deprecated
    public String extractUsername(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        return String.valueOf(jwt.getPayload("username"));
    }

    public boolean validateToken(String token) {
        String key = CACHE_PREFIX+token;
        // 如果key未过期且通过签名校验，则验证通过
        if(JWTUtil.verify(token,SECRET.getBytes()) && redisTemplate.hasKey(key)){
            // 刷新token时间
            redisTemplate.expire(key,Duration.ofMinutes(30));
            return true;
        }
        return false;
    }

}
