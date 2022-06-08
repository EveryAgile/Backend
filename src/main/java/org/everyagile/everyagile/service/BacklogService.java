//package org.everyagile.everyagile.service;
//
//import io.swagger.v3.oas.annotations.servers.Server;
//import lombok.RequiredArgsConstructor;
//import org.everyagile.everyagile.advice.CBacklogNotExistedException;
//import org.everyagile.everyagile.advice.CSprintNotExistedException;
//import org.everyagile.everyagile.advice.CUsernameNotFoundException;
//import org.everyagile.everyagile.domain.Backlog;
//import org.everyagile.everyagile.domain.Sprint;
//import org.everyagile.everyagile.domain.User;
//import org.everyagile.everyagile.dto.BacklogRequestDto;
//import org.everyagile.everyagile.repository.BacklogRepository;
//import org.everyagile.everyagile.repository.SprintRepository;
//import org.everyagile.everyagile.repository.UserRepository;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Server
//@RequiredArgsConstructor
//public class BacklogService {
//    private final BacklogRepository backlogRepository;
//    private final UserRepository userRepository;
//    private final SprintRepository sprintRepository;
//
//    @Transactional
//    public Backlog createBacklog(String email, BacklogRequestDto requestDto){
//        Sprint sprint = sprintRepository.findById(requestDto.getSprintId()).orElseThrow(CSprintNotExistedException::new);
//        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
//        Backlog backlog = new Backlog(requestDto, false, sprint);
//        user.addBacklog(backlog);
//        sprint.addBacklog(backlog);
//        backlogRepository.save(backlog);
//        userRepository.save(user);
//        sprintRepository.save(sprint);
//        return backlog;
//    }
//
//    public Backlog getBacklog(String email, Long backlogId){
//        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
//        Backlog backlog = backlogRepository.findById(backlogId).orElseThrow(CBacklogNotExistedException::new);
//        return backlog;
//    }
//
//    @Transactional
//    public Backlog setBacklogStatus(Long backlogId, boolean status, String email) {
//        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
//        Backlog backlog = backlogRepository.findById(backlogId).orElseThrow(CBacklogNotExistedException::new);
//        backlog.setBacklogStatus(status);
//        return backlogRepository.save(backlog);
//    }
//
//    @Transactional
//    public void deleteBacklog(Long backlogId,String email) {
//        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
//        Backlog backlog = backlogRepository.findById(backlogId).orElseThrow(CBacklogNotExistedException::new);
//        Sprint sprint = backlog.getSprint();
//        sprint.deleteBacklog(backlog);
//        sprintRepository.save(sprint);
//        user.deleteBacklog(backlog);
//        userRepository.save(user);
//        backlogRepository.delete(backlog);
//    }
//
//    public List<Backlog> getAllBacklog(Long backlogId,String email) {
//        Sprint sprint = sprintRepository.findById(backlogId).orElseThrow(CBacklogNotExistedException::new);
//        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
//        return sprint.getBacklogs();
//    }
//}
