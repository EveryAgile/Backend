package org.everyagile.everyagile.service;

import lombok.RequiredArgsConstructor;
import org.everyagile.everyagile.advice.CBacklogNotExistedException;
import org.everyagile.everyagile.advice.CTaskNotExistedException;
import org.everyagile.everyagile.advice.CUsernameNotFoundException;
import org.everyagile.everyagile.domain.*;
import org.everyagile.everyagile.dto.TaskRequestDto;
import org.everyagile.everyagile.dto.responseDto.ProjectResponseDto;
import org.everyagile.everyagile.dto.responseDto.TaskResponseDto;
import org.everyagile.everyagile.dto.responseDto.UserResponseDto;
import org.everyagile.everyagile.repository.BacklogRepository;
import org.everyagile.everyagile.repository.TaskRepository;
import org.everyagile.everyagile.repository.UserRepository;
import org.everyagile.everyagile.repository.UserTaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final UserTaskRepository userTaskRepository;
    private final BacklogRepository backlogRepository;

    // 할일 생성
    @Transactional
    public TaskResponseDto createTask(String email, TaskRequestDto requestDto) {
        Backlog backlog = backlogRepository.findById(requestDto.getBacklogId()).orElseThrow(CBacklogNotExistedException::new);
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Task task = new Task(requestDto, false, backlog);
        taskRepository.save(task);
        backlog.addTask(task);
        backlogRepository.save(backlog);
//        List<String> emails = requestDto.getUsers();
//        for(String e: emails){
//            User member = userRepository.findByEmail(e).orElseThrow(CUsernameNotFoundException::new);
//            UserTask userTask = new UserTask(member, task);
//            userTaskRepository.save(userTask);
//        }
        return new TaskResponseDto(task);
    }

    // 모든 할일 조회
    public List<TaskResponseDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskResponseDto> responseDtos = new ArrayList<>();
        for(Task task: tasks){
            responseDtos.add(new TaskResponseDto(task));
        }
        return responseDtos;
    }

    // 할일 단건 조회
    public TaskResponseDto getTask(String email, Long taskId) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Task task = taskRepository.findById(taskId).orElseThrow(CTaskNotExistedException::new);
        return new TaskResponseDto(task);
    }

    // 할일 완료 여부 수정
    @Transactional
    public TaskResponseDto setTaskStatus(Long taskId, boolean status, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Task task = taskRepository.findById(taskId).orElseThrow(CTaskNotExistedException::new);
        task.setTaskStatus(status);
        return new TaskResponseDto(taskRepository.save(task));
    }

    // 할일 삭제
    @Transactional
    public void deleteTask(Long taskId, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Task task = taskRepository.findById(taskId).orElseThrow(CTaskNotExistedException::new);
        Backlog backlog = task.getBacklog();
        backlog.deleteTask(task);
        backlogRepository.save(backlog);
        taskRepository.delete(task);
        userTaskRepository.deleteAllByTask(task);
    }

    // 백로그의 모든 할일 조회
    public List<TaskResponseDto> getAllTask(Long backlogId, String email){
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        Backlog backlog = backlogRepository.findById(backlogId).orElseThrow(CBacklogNotExistedException::new);
        List<Task> tasks = backlog.getTasks();
        List<TaskResponseDto> taskResponseDtos = new ArrayList<>();
        for(Task task: tasks){
            taskResponseDtos.add(new TaskResponseDto(task));
        }
        return taskResponseDtos;
    }

//    // 할일 담당자 조회
//    public List<UserResponseDto> getAllUser(Long taskId, String email){
//        Task task = taskRepository.findById(taskId).orElseThrow(CTaskNotExistedException::new);
//        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
//        List<UserResponseDto> users = new ArrayList<>();
//        List<UserTask> userTasks = userTaskRepository.findAllByTask(task);
//        for(UserTask userTask: userTasks){
//            users.add(new UserResponseDto(userTask.getUser()));
//        }
//        return users;
//    }
}
