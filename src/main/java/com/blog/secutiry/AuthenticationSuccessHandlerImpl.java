package com.blog.secutiry;

import cn.hutool.json.JSONUtil;
import com.blog.common.R;
import com.blog.model.dto.security.UserDetailsDTO;
import com.blog.model.dto.security.UserInfoDTO;
import com.blog.service.TokenService;
import com.blog.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    private TokenService tokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserInfoDTO userInfoDTO = new UserInfoDTO();

        BeanUtils.copyProperties(SecurityUtils.getUserDetailsDTO(), userInfoDTO);

        if (Objects.nonNull(authentication)) {
            UserDetailsDTO userDetailsDTO = (UserDetailsDTO) authentication.getPrincipal();
            String token = tokenService.createToken(userDetailsDTO.getUser().getId().toString());
            userInfoDTO.setToken(token);
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSONUtil.toJsonStr(R.success(userInfoDTO)));
    }

}
