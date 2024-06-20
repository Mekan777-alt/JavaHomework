package org.example.hm3.configuration;

import jakarta.annotation.PostConstruct;
import org.example.hm3.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {
    @Autowired
    private CategoryService categoryService;

    public static Long count = 0L;

    @PostConstruct
    public void init() {
        CommonConfig.count = categoryService.categoryID();
        System.out.println(CommonConfig.count);
    }
}
