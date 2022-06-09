package org.everyagile.everyagile.service;

import lombok.RequiredArgsConstructor;
import org.everyagile.everyagile.advice.CNotMemberException;
import org.everyagile.everyagile.advice.CProjectNotExistedException;
import org.everyagile.everyagile.advice.CUsernameNotFoundException;
import org.everyagile.everyagile.domain.Project;
import org.everyagile.everyagile.domain.User;
import org.everyagile.everyagile.domain.UserProject;
import org.everyagile.everyagile.dto.InviteRequestDto;
import org.everyagile.everyagile.dto.ProjectRequestDto;
import org.everyagile.everyagile.dto.responseDto.ProjectResponseDto;
import org.everyagile.everyagile.dto.responseDto.UserResponseDto;
import org.everyagile.everyagile.repository.ProjectRepository;
import org.everyagile.everyagile.repository.UserProjectRepository;
import org.everyagile.everyagile.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final UserProjectRepository userProjectRepository;

    // 프로젝트 생성
    @Transactional
    public ProjectResponseDto createProject(String email, ProjectRequestDto requestDto) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Project project = new Project(requestDto);
        projectRepository.save(project);
        UserProject userProject = new UserProject(user, project);
        userProjectRepository.save(userProject);
        return new ProjectResponseDto(project);
    }

    // 프로젝트 조회
    public ProjectResponseDto getProjectByIdx(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(CProjectNotExistedException::new);
        return new ProjectResponseDto(project);
    }

    // 모든 프로젝트 조회
    public List<ProjectResponseDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectResponseDto> responseDtos = new ArrayList<>();
        for(Project project: projects){
            responseDtos.add(new ProjectResponseDto(project));
        }
        return responseDtos;
    }

    // 프로젝트 삭제
    @Transactional
    public void deleteProjectByIdx(Long projectId, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Project project = projectRepository.findById(projectId).orElseThrow(CProjectNotExistedException::new);
        projectRepository.deleteById(projectId);
        userProjectRepository.deleteAllByProject(project);
    }

    // 멤버 초대
    @Transactional
    public void inviteMember(String email, InviteRequestDto requestDto) {
        Project project = projectRepository.findById(requestDto.getProjectId()).orElseThrow(CProjectNotExistedException::new);
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        User member = userRepository.findByEmail(requestDto.getMemberEmail()).orElseThrow(CUsernameNotFoundException::new);
        UserProject userProject = new UserProject(member, project);
        userProjectRepository.save(userProject);
    }

    // 멤버 조회
    public List<UserResponseDto> getMembers(Long projectId, String email){
        Project project = projectRepository.findById(projectId).orElseThrow(CProjectNotExistedException::new);
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        List<UserResponseDto> users = new ArrayList<>();
        List<UserProject> userProjects =  userProjectRepository.findAllByProject(project);
        for(UserProject group : userProjects) {
            User member = group.getUser();
            users.add(new UserResponseDto(member));
        }
        return users;
    }
}
