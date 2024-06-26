package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.enums.user.UserEnum;
import com.blog.enums.user.UserErrorEnum;
import com.blog.exception.APIException;
import com.blog.mapper.UserMapper;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.security.UserDetailsDTO;
import com.blog.model.dto.user.RegisterDTO;
import com.blog.model.dto.user.UpdateDTO;
import com.blog.model.dto.user.UserDTO;
import com.blog.pojo.User;
import com.blog.service.TokenService;
import com.blog.service.UserRoleService;
import com.blog.service.UserService;
import com.blog.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RedisService redisService;

    @Override
    public void register(RegisterDTO dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = this.getOne(queryWrapper);

        if (user != null) {
            throw new APIException(Constant.USERNAME_ALREADY_ERROR);
        }

        user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setNickname(UUID.randomUUID().toString().replaceAll("-", ""));
        user.setIsDisable(UserEnum.NORMAL.getCode());
        user.setLastLoginTime(LocalDateTime.now());
        this.save(user);
    }

    @Override
    public UserDTO getByPage(PageDTO dto) {
        Page<User> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        String keyword = dto.getKeyword();

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(User::getUsername, keyword);
        queryWrapper.select(User.class, i -> !"password".equals(i.getColumn()));
        queryWrapper.orderByDesc(User::getUpdateTime);
        queryWrapper.orderByDesc(User::getCreateTime);

        UserDTO userDTO = new UserDTO();
        userDTO.setPage(this.page(page, queryWrapper));
        return userDTO;
    }

    @Override
    public void disable(Long id, Integer isDisable) {
        if (!UserEnum.DISABLE.getCode().equals(isDisable) && !UserEnum.NORMAL.getCode().equals(isDisable)) {
            throw new APIException(UserErrorEnum.DISABLE_USER_ERROR.getValue());
        }
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, id);
        updateWrapper.set(User::getIsDisable, isDisable);
        this.update(updateWrapper);
        if (UserEnum.DISABLE.getCode().equals(isDisable)) {
            tokenService.delLoginUser(String.valueOf(id));
        }
    }

    @Override
    public void edit(UpdateDTO dto) {
        Long id = dto.getId();

        if (this.getById(id) == null) {
            throw new APIException(UserErrorEnum.NOT_FOUND_USER_ERROR.getValue());
        }

        String nickname = dto.getNickname();
        List<Long> roleIds = dto.getRoleIds();

        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(User::getNickname, nickname);
        updateWrapper.eq(User::getId, id);
        this.update(updateWrapper);

        userRoleService.updateByUserId(id, roleIds);
    }

    @Override
    public UserDTO onlinePage(PageDTO dto) {
        Map<String, Object> userDTOMap = redisService.hGetAll(Constant.REDIS_USER_KEY);

        Collection<Object> userDTOs = userDTOMap.values();
        String keyword = dto.getKeyword();
        Integer pageSize = dto.getPageSize();
        Integer pageNum = dto.getPageNum();

        Page<User> page = new Page<>();

        List<User> users = new ArrayList<>();

        for (Object userDTO : userDTOs) {
            UserDetailsDTO userDetailsDTO = (UserDetailsDTO) userDTO;
            if (userDetailsDTO.getUser().getNickname().contains(keyword)) {
                users.add(userDetailsDTO.getUser());
            }
        }

        page.setTotal(users.size());
        page.setRecords(users.subList((pageNum - 1) * pageSize, Math.min((pageNum * pageSize), users.size())));
        UserDTO userDTO = new UserDTO();
        userDTO.setPage(page);
        return userDTO;
    }

    @Override
    public void offline(Long id) {
        redisService.hDel(Constant.REDIS_USER_KEY, id.toString());
    }

}