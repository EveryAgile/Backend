package org.everyagile.everyagile.service;

import lombok.RequiredArgsConstructor;
import org.everyagile.everyagile.advice.CNotMemberException;
import org.everyagile.everyagile.advice.CProjectNotExistedException;
import org.everyagile.everyagile.advice.CUsernameNotFoundException;
import org.everyagile.everyagile.domain.Project;
import org.everyagile.everyagile.domain.User;
import org.everyagile.everyagile.dto.InviteRequestDto;
import org.everyagile.everyagile.dto.ProjectRequestDto;
import org.everyagile.everyagile.repository.ProjectRepository;
import org.everyagile.everyagile.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Transactional
    public Project createProject(String email, ProjectRequestDto requestDto) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Project project = new Project(requestDto, user);
        return projectRepository.save(project);
    }

    public Project getProjectByIdx(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(CProjectNotExistedException::new);
        return project;
    }

    @Transactional
    public void deleteProjectByIdx(Long projectId, String email) {
        Project project = projectRepository.findById(projectId).orElseThrow(CProjectNotExistedException::new);
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Boolean isMember = project.getUsers().contains(user);
        if(!isMember.equals(true)){
            throw new CNotMemberException();
        }
        projectRepository.deleteById(projectId);
    }

    @Transactional
    public Project inviteMember(String email, InviteRequestDto requestDto) {
        Project project = projectRepository.findById(requestDto.getProjectId()).orElseThrow(CProjectNotExistedException::new);
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Boolean isMember = project.getUsers().contains(user);
        if(!isMember.equals(true)){
            throw new CNotMemberException();
        }
        User member = userRepository.findByEmail(requestDto.getMemberEmail()).orElseThrow(CUsernameNotFoundException::new);
        project.addUser(member);
        return project;
    }
}
