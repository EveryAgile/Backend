package org.everyagile.everyagile.everyagile.service;

import org.everyagile.everyagile.everyagile.entity.Backlog;
import org.everyagile.everyagile.everyagile.dto.TaskDto;
import org.everyagile.everyagile.everyagile.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.everyagile.everyagile.everyagile.entity.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@EnableTransactionManagement
public class TaskService {

    private TaskRepository taskRepository;

    @Transactional
    private TaskDto convertEntityToDto(Task task) {
        TaskDto taskDto = TaskDto.builder()
                .id(task.getId())
                .backlog(task.getBacklog())
                .name(task.getName())
                .status(task.isStatus())
                .md(task.getMd())
                .createdDate(task.getCreatedDate())
                .modifiedDate(task.getModifiedDate())
                .build();
        return taskDto;
    }

    @Transactional
    public TaskDto getTask (Long taskid) {
        Optional<Task> task_wrapper = taskRepository.findById(taskid);
        Task task = task_wrapper.get();
        return this.convertEntityToDto(task);
    }

    @Transactional
    public  List<TaskDto> getTaskList(Backlog backlog) {

        List<Task> taskList = taskRepository.findByBacklog(backlog);
        List<TaskDto> taskDtoList = new ArrayList<>();
        for (Task task : taskList) {
            taskDtoList.add(this.convertEntityToDto(task));
        }
        return taskDtoList;
    }

    @Transactional
    public Long saveTask(TaskDto taskDto) {
        return taskRepository.save(taskDto.toEntity()).getId();
    }

    @Transactional
    public void deleteTask(Long taskid){
        TaskDto taskDto = this.getTask(taskid);
        Task task = taskDto.toEntity();
//        BacklogDto backlogDto = backlogService.getBacklog(backlogid);
//        Backlog backlog = backlogDto.toEntity();
        //Backlog backlog = em.find(Backlog.class , )
        Backlog backlog = task.getBacklog();
        backlog.removeTask(task);
        //em.flush();
        taskRepository.deleteById(taskid);
    }
}
