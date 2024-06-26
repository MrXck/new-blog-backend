package com.blog.service.impl;

import com.blog.model.dto.security.UserDetailsDTO;
import com.blog.service.TokenService;
import com.blog.utils.CacheUtils;
import com.blog.utils.Constant;
import com.blog.utils.JwtUtils;
import com.blog.utils.UserThreadLocal;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CacheUtils<String, Object> cacheUtils;

    @Override
    public String createToken(UserDetailsDTO userDetailsDTO) {
        cacheUtils.hSet(Constant.REDIS_USER_KEY, userDetailsDTO.getUser().getId().toString(), userDetailsDTO, 120, TimeUnit.MINUTES);
        return jwtUtils.createToken(userDetailsDTO.getUser().getId().toString());
    }

    @Override
    public void refreshToken(String id) {
        Object o = cacheUtils.hGet(Constant.REDIS_USER_KEY, id);
        cacheUtils.hSet(Constant.REDIS_USER_KEY, id, o, 120, TimeUnit.MINUTES);
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
            return (UserDetailsDTO) cacheUtils.hGet(Constant.REDIS_USER_KEY, userId);
        }
        return null;
    }

    @Override
    public void delLoginUser(String id) {
        cacheUtils.hDel(Constant.REDIS_USER_KEY, id);
    }
}
