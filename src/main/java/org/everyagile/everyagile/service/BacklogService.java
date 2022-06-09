package org.everyagile.everyagile.service;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.everyagile.everyagile.advice.CBacklogNotExistedException;
import org.everyagile.everyagile.advice.CSprintNotExistedException;
import org.everyagile.everyagile.advice.CUsernameNotFoundException;
import org.everyagile.everyagile.domain.*;
import org.everyagile.everyagile.dto.BacklogRequestDto;
import org.everyagile.everyagile.dto.responseDto.BacklogResponseDto;
import org.everyagile.everyagile.dto.responseDto.ProjectResponseDto;
import org.everyagile.everyagile.dto.responseDto.UserResponseDto;
import org.everyagile.everyagile.repository.BacklogRepository;
import org.everyagile.everyagile.repository.SprintRepository;
import org.everyagile.everyagile.repository.UserBacklogRepository;
import org.everyagile.everyagile.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BacklogService {
    private final BacklogRepository backlogRepository;
    private final UserRepository userRepository;
    private final SprintRepository sprintRepository;
    private final UserBacklogRepository userBacklogRepository;

    // 백로그 생성
    @Transactional
    public BacklogResponseDto createBacklog(String email, BacklogRequestDto requestDto){
        Sprint sprint = sprintRepository.findById(requestDto.getSprintId()).orElseThrow(CSprintNotExistedException::new);
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Backlog backlog = new Backlog(requestDto, false, sprint);
        backlogRepository.save(backlog);
        sprint.addBacklog(backlog);
        sprintRepository.save(sprint);
        List<String> emails = requestDto.getUsers();
        for(String e : emails){
            User member = userRepository.findByEmail(e).orElseThrow(CUsernameNotFoundException::new);
            UserBacklog userBacklog = new UserBacklog(member, backlog);
            userBacklogRepository.save(userBacklog);
        }
        return new BacklogResponseDto(backlog);
    }

    // 모든 백로그 조회
    public List<BacklogResponseDto> getAllBacklogs() {
        List<Backlog> backlogs = backlogRepository.findAll();
        List<BacklogResponseDto> responseDtos = new ArrayList<>();
        for(Backlog backlog: backlogs){
            responseDtos.add(new BacklogResponseDto(backlog));
        }
        return responseDtos;
    }

    // 백로그 단건 조회
    public BacklogResponseDto getBacklog(String email, Long backlogId){
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Backlog backlog = backlogRepository.findById(backlogId).orElseThrow(CBacklogNotExistedException::new);
        return new BacklogResponseDto(backlog);
    }

    // 백로그 완료 여부 수정
    @Transactional
    public BacklogResponseDto setBacklogStatus(Long backlogId, boolean status, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Backlog backlog = backlogRepository.findById(backlogId).orElseThrow(CBacklogNotExistedException::new);
        backlog.setBacklogStatus(status);
        return new BacklogResponseDto(backlogRepository.save(backlog));
    }

    // 백로그 삭제
    @Transactional
    public void deleteBacklog(Long backlogId,String email) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Backlog backlog = backlogRepository.findById(backlogId).orElseThrow(CBacklogNotExistedException::new);
        Sprint sprint = backlog.getSprint();
        sprint.deleteBacklog(backlog);
        sprintRepository.save(sprint);
        backlogRepository.delete(backlog);
        userBacklogRepository.deleteAllByBacklog(backlog);
    }

    // 스프린트의 모든 백로그 조회
    public List<BacklogResponseDto> getAllBacklog(Long sprintId, String email) {
        Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(CSprintNotExistedException::new);
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        List<Backlog> backlogs = sprint.getBacklogs();
        List<BacklogResponseDto> backlogResponseDtos = new ArrayList<>();
        for(Backlog backlog: backlogs){
            backlogResponseDtos.add(new BacklogResponseDto(backlog));
        }
        return backlogResponseDtos;
    }

    // 백로그 담당자 조회
    public List<UserResponseDto> getAllUser(Long backlogId,String email) {
        Backlog backlog = backlogRepository.findById(backlogId).orElseThrow(CBacklogNotExistedException::new);
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        List<UserResponseDto> users = new ArrayList<>();
        List<UserBacklog> userBacklogs = userBacklogRepository.findAllByBacklog(backlog);
        for(UserBacklog userBacklog: userBacklogs) {
            users.add(new UserResponseDto(userBacklog.getUser()));
        }
        return users;
    }
}
