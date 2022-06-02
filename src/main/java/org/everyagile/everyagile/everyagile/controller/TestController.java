package org.everyagile.everyagile.everyagile.controller;

import org.everyagile.everyagile.everyagile.entity.Backlog;
import org.everyagile.everyagile.everyagile.entity.Project;
import org.everyagile.everyagile.everyagile.entity.Sprint;
import org.everyagile.everyagile.everyagile.service.ProjectService;
import org.everyagile.everyagile.everyagile.service.SprintService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.everyagile.everyagile.everyagile.dto.BacklogDto;
import org.everyagile.everyagile.everyagile.dto.ProjectDto;
import org.everyagile.everyagile.everyagile.dto.SprintDto;
import org.everyagile.everyagile.everyagile.dto.TaskDto;
import org.everyagile.everyagile.everyagile.service.BacklogService;
import org.everyagile.everyagile.everyagile.service.TaskService;
import java.util.List;

@Controller
@RequestMapping("test")
@AllArgsConstructor

public class TestController {


    ProjectService projectService;
    SprintService sprintService;
    BacklogService backlogService;
    TaskService taskService;

    @GetMapping("")
    @ResponseBody
    public String Hello() {
        return "Hello";
    }

    @GetMapping("/get/project/{projectid}")
    @ResponseBody
    public String getProject(@PathVariable("projectid") Long projectid) {
        ProjectDto projectDto = projectService.getProject(projectid);
        return projectDto.toString();
    }
    @GetMapping("/get/sprints/{projectid}")
    @ResponseBody
    public String getSprintList(@PathVariable("projectid") Long projectid) {
        ProjectDto projectDto = projectService.getProject(projectid);
        Project project = projectDto.toEntity();
        List<SprintDto> sprints= sprintService.getSprintList(project);
        String temp = "";
        for (SprintDto sprintDto : sprints){
            temp += sprintDto.toString();
        }
        return temp;
    }

    @GetMapping("/get/backlogs/{sprintid}")
    @ResponseBody
    public String getBacklogList(@PathVariable("sprintid") Long sprintid) {
        SprintDto sprintDto = sprintService.getSprint(sprintid);
        Sprint sprint = sprintDto.toEntity();
        List<BacklogDto> backlogs = backlogService.getBacklogList(sprint);
        String temp = "";
        for (BacklogDto backlogDto : backlogs){
            temp += backlogDto.toString();
        }
        return temp;
    }

    @GetMapping("/get/tasks/{backlogid}")
    @ResponseBody
    public String getTaskList(@PathVariable("backlogid") Long backlogid) {
        BacklogDto backlogDto = backlogService.getBacklog(backlogid);
        Backlog backlog = backlogDto.toEntity();
        List<TaskDto> tasks = taskService.getTaskList(backlog);
        String temp = "";
        for (TaskDto taskDto : tasks) {
            temp += taskDto.toString();
        }
        return temp;
    }
    @GetMapping(value = "/write/project")
    @ResponseBody
    public Long writeProject() {
        ProjectDto projectDto = ProjectDto.builder()
                .projectname("test")
                .build();
        return projectService.saveProject(projectDto);
    }


    @GetMapping(value = "/write/projectid/{projectid}/sprint")
    @ResponseBody
    public Long writeSprint(@PathVariable("projectid") Long projectid) {
        ProjectDto projectDto = projectService.getProject(projectid);
        Project project = projectDto.toEntity();

        SprintDto sprintDto = SprintDto.builder()
                .description("test")
                .importance(Long.valueOf(5))
                .project(project)
                .name("sprint")
                .status(true)
                .build();
        //project.addSprint(sprintDto.toEntity());
        project.addSprint(sprintDto.toEntity());
        return sprintService.saveSprint(sprintDto);

    }

    @GetMapping(value = "/write/sprintid/{sprintid}/Backlog")
    @ResponseBody
    public Long writeBacklog(@PathVariable("sprintid") Long sprintid) {
        SprintDto sprintDto = sprintService.getSprint(sprintid);
        Sprint sprint = sprintDto.toEntity();

        BacklogDto backlogDto = BacklogDto.builder()
                .sprint(sprint)
                .storypoint(3.4)
                .md(2.2)
                .name("backlog")
                .status(true)
                .build();
        sprint.addBacklog(backlogDto.toEntity());
        return backlogService.saveBacklog(backlogDto);
    }

    @GetMapping(value = "/write/backlogid/{backlogid}/Task")
    @ResponseBody
    public Long writeTask(@PathVariable("backlogid") Long backlogid) {
        BacklogDto backlogDto = backlogService.getBacklog(backlogid);
        Backlog backlog = backlogDto.toEntity();

        TaskDto taskDto = TaskDto.builder()
                .backlog(backlog)
                .name("task")
                .status(false)
                .md(3.1)
                .build();
        backlog.addTask(taskDto.toEntity());
        return taskService.saveTask(taskDto);
    }

    @GetMapping("/delete/projectid/{projectid}")
    @ResponseBody
    public void deleteProject(@PathVariable("projectid") Long projectid) {

        projectService.deleteProject(projectid);
    }

    @GetMapping("/delete/sprintid/{projectid}/{sprintid}")
    @ResponseBody
    public void deleteSprint(@PathVariable("sprintid") Long sprintid) {

        sprintService.deleteSprint(sprintid);
    }

    @GetMapping("/delete/backlogid/{backlogid}")
    @ResponseBody
    public void deleteBacklog(@PathVariable("backlogid") Long backlogid) {

        backlogService.deleteBacklog(backlogid);
    }

    @GetMapping("/delete/taskid/{taskid}")
    @ResponseBody
    public void deleteTask(@PathVariable("taskid") Long taskid) {

        taskService.deleteTask(taskid);
    }





}
