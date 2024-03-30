package com.blog.model.dto.security;

import lombok.Data;

import java.util.List;

@Data
public class ResourceRoleDTO {

    private Long id;

    private String path;

    private String method;

    private List<String> roles;
}
