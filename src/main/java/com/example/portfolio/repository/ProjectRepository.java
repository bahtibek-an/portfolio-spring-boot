package com.example.portfolio.repository;

import com.example.portfolio.entity.ProjectEntity;
import com.example.portfolio.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {
    ProjectEntity findByTitle(String title);

}
