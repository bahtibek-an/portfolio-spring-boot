package com.example.portfolio.controllers;

import com.example.portfolio.entity.ProjectEntity;
import com.example.portfolio.model.Project;
import com.example.portfolio.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    public static String uploadDirectory = System.getProperty("user.dir") + "src/main/static";


    @GetMapping("/projects")
    public String projects(Model model) {
        Iterable<ProjectEntity> projects = projectService.getAll();
        model.addAttribute("projects", projects);
        return "project/index";
    }

    @GetMapping("/projects/admin")
    public String projectsAll(Model model) {
        List<Project> entities = projectService.getOwnProjects().getProjects();
        model.addAttribute("projects", entities);
        return "project/admin";
    }

    @PostMapping("/projects/admin/{id}")
    public String deleteProject(@PathVariable(value = "id") Long id) {
        projectService.deleteProject(id);
        return "redirect:/projects/admin";
    }

    @GetMapping("/projects/admin/{id}/edit")
    public String updateProjectView(@PathVariable(value = "id") Long id, Model model) {
        ArrayList<ProjectEntity> entity = projectService.getOne(id);
        model.addAttribute("project", entity.get(0));
        return "project/edit";
    }

    @PostMapping("/projects/admin/{id}/edit")
    public String updateProject(ProjectEntity entity, @PathVariable(value = "id") Long id) {
        projectService.updateProject(entity, id);
        return "redirect:/projects/admin";
    }

    @GetMapping("projects/new")
    public String addProjectView() {
        return "project/new";
    }

    @PostMapping("projects/new")
    public String createProject(ProjectEntity entity, Map<String, Object> model) {
        try {
            Project project = projectService.create(entity);
            model.put("message", "Project successfully created");
        } catch (Exception e) {
            model.put("error", e.getMessage());
        }

        return "redirect:/projects/admin";
    }

}
