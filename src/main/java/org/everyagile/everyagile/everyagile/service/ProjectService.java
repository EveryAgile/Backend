package org.everyagile.everyagile.everyagile.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.everyagile.everyagile.everyagile.entity.Project;
import org.everyagile.everyagile.everyagile.dto.ProjectDto;
import org.everyagile.everyagile.everyagile.repository.ProjectRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    @Transactional
    private ProjectDto convertEntityToDto(Project project) {
        ProjectDto projectDto = ProjectDto.builder()
                .id(project.getId())
                .groupid(project.getGroupid())
                .projectname(project.getProjectname())
                .type(project.getType())
                .enddate(project.getEnddate())
                .startdate(project.getStartdate())
                .createdDate(project.getCreatedDate())
                .modifiedDate(project.getModifiedDate())
                .build();
        return projectDto;
    }

    @Transactional
    public ProjectDto getProject(Long projectid) {

        Optional<Project> project_wrapper = projectRepository.findById(projectid);
        Project project = project_wrapper.get();
        return this.convertEntityToDto(project);
    }

//   // public List<Sprint> getSprintList(ProjectDto projectDto) {
//        return projectDto.getSprints();
//    }

    @Transactional
    public Long saveProject(ProjectDto projectDto) {
//        Project project = new Project();
//        project = projectDto.toEntity();
//        UniqueEntityManager.em.persist(project);
//        UniqueEntityManager.em.flush();
//        return project.getId();
        return projectRepository.save(projectDto.toEntity()).getId();
    }

    @Transactional
    public void deleteProject(Long Project_id){

        projectRepository.deleteById(Project_id);

    }

    //@Transactional
//      public  getGroupIdList (Long Project_id) {
//    }




}
