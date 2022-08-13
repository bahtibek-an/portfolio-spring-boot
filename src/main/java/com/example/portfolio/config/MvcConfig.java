package com.example.portfolio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewController(ViewControllerRegistry registry) {
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path imageUploadDir = Paths.get("./pictures");
        String imageUploadPath = imageUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/pictures/**").addResourceLocations("file:/" + imageUploadPath + "/");
    }
}
