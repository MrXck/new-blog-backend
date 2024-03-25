package com.blog.dto.user;

import com.blog.pojo.User;
import lombok.Data;

@Data
public class UserDTO {

    private User user;

    private String token;
}
