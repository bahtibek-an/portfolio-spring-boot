package com.example.portfolio.model;

import com.example.portfolio.entity.ProjectEntity;
import com.example.portfolio.entity.UserEntity;

import javax.persistence.Transient;
import java.util.List;
import java.util.stream.Collectors;

public class Project {
    private Long id;
    private String link;
    private String title;
    private String description;

    private Long userId;

    private String image;


    public static Project toModel(ProjectEntity entity) {
        Project model = new Project();
        model.setId(entity.getId());
        model.setLink(entity.getLink());
        model.setTitle(entity.getTitle());
        model.setDescription(entity.getDescription());
        model.setUserId(entity.getUser().getId());
        model.setImage(entity.getImage());
        return model;
    }

    public Project() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    public String getProjectImagePath() {
        if(image == null || id == null) return null;

        return "/pictures/" + id + "/" + image;
    }
}
