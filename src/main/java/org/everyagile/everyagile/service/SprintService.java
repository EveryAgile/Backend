package org.everyagile.everyagile.service;

import lombok.RequiredArgsConstructor;
import org.everyagile.everyagile.advice.CNotMemberException;
import org.everyagile.everyagile.advice.CProjectNotExistedException;
import org.everyagile.everyagile.advice.CSprintNotExistedException;
import org.everyagile.everyagile.advice.CUsernameNotFoundException;
import org.everyagile.everyagile.domain.Project;
import org.everyagile.everyagile.domain.Sprint;
import org.everyagile.everyagile.domain.User;
import org.everyagile.everyagile.domain.UserSprint;
import org.everyagile.everyagile.dto.SprintRequestDto;
import org.everyagile.everyagile.dto.responseDto.SprintResponseDto;
import org.everyagile.everyagile.dto.responseDto.UserResponseDto;
import org.everyagile.everyagile.repository.ProjectRepository;
import org.everyagile.everyagile.repository.SprintRepository;
import org.everyagile.everyagile.repository.UserRepository;
import org.everyagile.everyagile.repository.UserSprintRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SprintService {
    private final SprintRepository sprintRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final UserSprintRepository userSprintRepository;

    // 스프린트 생성
    @Transactional
    public SprintResponseDto createSprint(String email, SprintRequestDto requestDto) {
        Project project = projectRepository.findById(requestDto.getProjectId()).orElseThrow(CProjectNotExistedException::new);
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Sprint sprint = new Sprint(requestDto, false, project);
        sprintRepository.save(sprint);
        List<String> emails = requestDto.getUsers();
        for(String e : emails){
            User member = userRepository.findByEmail(e).orElseThrow(CUsernameNotFoundException::new);
            UserSprint userSprint = new UserSprint(member, sprint);
            userSprintRepository.save(userSprint);
        }
        return new SprintResponseDto(sprint);
    }

    // 스프린트 조회
    public SprintResponseDto getSprint(String email, Long sprintId) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(CSprintNotExistedException::new);
        return new SprintResponseDto(sprint);
    }

    // 스프린트 완료 여부 수정
    @Transactional
    public SprintResponseDto setSprintStatus(Long sprintId, boolean status, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(CSprintNotExistedException::new);
        sprint.setSprintStatus(status);
        return new SprintResponseDto(sprintRepository.save(sprint));
    }

    // 스프린트 삭제
    @Transactional
    public void deleteSprint(Long sprintId, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(CSprintNotExistedException::new);
        sprintRepository.delete(sprint);
        userSprintRepository.deleteAllBySprint(sprint);
    }

    // 프로젝트의 모든 스프린트 조회
    public List<SprintResponseDto> getAllSprint(Long projectId, String email) {
        Project project = projectRepository.findById(projectId).orElseThrow(CProjectNotExistedException::new);
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        List<Sprint> sprints =  project.getSprints();
        List<SprintResponseDto> sprintResponseDtos = new ArrayList<>();
        for(Sprint sprint : sprints) {
            sprintResponseDtos.add(new SprintResponseDto(sprint));
        }
        return sprintResponseDtos;
    }

    // 스프린트 담장자 조회
    public List<UserResponseDto> getAllUser(Long sprintId, String email) {
        Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(CSprintNotExistedException::new);
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        List<UserResponseDto> users = new ArrayList<>();
        List<UserSprint> userSprints = userSprintRepository.findAllBySprint(sprint);
        for(UserSprint userSprint : userSprints){
            users.add(new UserResponseDto(userSprint.getUser()));
        }
        return users;
    }
}
