package com.blog.service.impl;

import com.blog.model.dto.security.UserDetailsDTO;
import com.blog.service.RedisService;
import com.blog.service.TokenService;
import com.blog.utils.Constant;
import com.blog.utils.JwtUtils;
import com.blog.utils.UserThreadLocal;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RedisService redisService;

    @Override
    public String createToken(String id) {
        return jwtUtils.createToken(id);
    }

    @Override
    public void refreshToken(String id) {
        Object o = redisService.hGet(Constant.REDIS_USER_KEY, id);
        redisService.hSet(Constant.REDIS_USER_KEY, id, o, Constant.TOKEN_DEFAULT_EXPIRE_TIME);
    }

    @Override
    public Claims parseToken(String token) {
        return null;
    }

    @Override
    public UserDetailsDTO getUserDetailDTO(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && !token.isEmpty()) {
            String userId = jwtUtils.checkToken(token);
            UserThreadLocal.set(Long.valueOf(userId));
            return (UserDetailsDTO) redisService.hGet(Constant.REDIS_USER_KEY, userId);
        }
        return null;
    }

    @Override
    public void delLoginUser(String id) {
        redisService.hDel(Constant.REDIS_USER_KEY, id);
    }
}
