package com.blog.model.dto.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.pojo.User;
import lombok.Data;

@Data
public class UserDTO {

    private User user;

    private String token;

    private Page<User> page;
}
