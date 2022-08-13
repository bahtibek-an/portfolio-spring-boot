package com.example.portfolio.service;

import com.example.portfolio.entity.ProjectEntity;
import com.example.portfolio.entity.UserEntity;
import com.example.portfolio.model.Project;
import com.example.portfolio.model.User;
import com.example.portfolio.repository.ProjectRepository;
import com.example.portfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public Project create(ProjectEntity entity) throws Exception{
        if(projectRepository.findByTitle(entity.getTitle()) != null) {
            throw new Exception("Project with this title already exists!");
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();

        UserEntity user = userRepository.findByUsername(username);
        UserEntity userEntity = userRepository.findById(user.getId()).get();
        entity.setUser(userEntity);

        return Project.toModel(projectRepository.save(entity));
    }

    public Iterable<ProjectEntity> getAll() {
        return projectRepository.findAll();
    }

    public User getOwnProjects() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = User.toModel(userRepository.findByUsername(username));

        return user;
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public Optional<ProjectEntity> updateProject (ProjectEntity entity, Long id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();

        UserEntity user = userRepository.findByUsername(username);
        UserEntity userEntity = userRepository.findById(user.getId()).get();
        entity.setUser(userEntity);

        return projectRepository.findById(id)
                .map(ProjectEntity -> {
                    return projectRepository.save(entity);
                });
    }

    public ArrayList<ProjectEntity> getOne(Long id) {
        Optional<ProjectEntity> project = projectRepository.findById(id);
        ArrayList<ProjectEntity> result = new ArrayList<>();
        project.ifPresent(result::add);

        return result;
    }
}
