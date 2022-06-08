package org.everyagile.everyagile.service;

import lombok.RequiredArgsConstructor;
import org.everyagile.everyagile.advice.CNotMemberException;
import org.everyagile.everyagile.advice.CProjectNotExistedException;
import org.everyagile.everyagile.advice.CSprintNotExistedException;
import org.everyagile.everyagile.advice.CUsernameNotFoundException;
import org.everyagile.everyagile.domain.Project;
import org.everyagile.everyagile.domain.Sprint;
import org.everyagile.everyagile.domain.User;
import org.everyagile.everyagile.dto.SprintRequestDto;
import org.everyagile.everyagile.repository.ProjectRepository;
import org.everyagile.everyagile.repository.SprintRepository;
import org.everyagile.everyagile.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SprintService {
    private final SprintRepository sprintRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public Sprint createSprint(String email, SprintRequestDto requestDto) {
        Project project = projectRepository.findById(requestDto.getProjectId()).orElseThrow(CProjectNotExistedException::new);
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Sprint sprint = new Sprint(requestDto, false, project);
        project.addSprint(sprint);
        user.addSprint(sprint);
        sprintRepository.save(sprint);
        projectRepository.save(project);
        userRepository.save(user);
        return sprint;
    }

    public Sprint getSprint(String email, Long sprintId) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(CSprintNotExistedException::new);
        return sprint;
    }

    @Transactional
    public Sprint setSprintStatus(Long sprintId, boolean status, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(CSprintNotExistedException::new);
        sprint.setSprintStatus(status);
        return sprintRepository.save(sprint);
    }

    @Transactional
    public void deleteSprint(Long sprintId, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(CSprintNotExistedException::new);
        Project project = sprint.getProject();
        project.deleteSprint(sprint);
        projectRepository.save(project);
        user.deleteSprint(sprint);
        userRepository.save(user);
        sprintRepository.delete(sprint);
    }

    public List<Sprint> getAllSprint(Long projectId, String email) {
        Project project = projectRepository.findById(projectId).orElseThrow(CProjectNotExistedException::new);
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        return project.getSprints();
    }
}
