package com.blog.model.dto.menu;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.pojo.Menu;
import lombok.Data;

import java.util.List;

@Data
public class MenuDTO {

    private Page<Menu> page;

    private Menu menu;

    private List<Menu> menus;

}