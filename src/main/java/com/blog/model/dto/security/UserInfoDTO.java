package com.blog.model.dto.security;

import com.blog.pojo.User;
import lombok.Data;

@Data
public class UserInfoDTO extends User {

    private String token;
}
