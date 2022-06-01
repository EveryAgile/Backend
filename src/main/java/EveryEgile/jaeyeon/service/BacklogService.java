package EveryEgile.jaeyeon.service;

import EveryEgile.jaeyeon.entity.Sprint;
import EveryEgile.jaeyeon.dto.BacklogDto;
import EveryEgile.jaeyeon.repository.BacklogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import EveryEgile.jaeyeon.entity.Backlog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BacklogService {

    private BacklogRepository backlogRepository;
    @Transactional
    private BacklogDto convertEntityToDto (Backlog backlog) {
        BacklogDto backlogDto = BacklogDto.builder()
                .id(backlog.getId())
                .sprint(backlog.getSprint())
                .storypoint(backlog.getStorypoint())
                .md(backlog.getMd())
                .name(backlog.getName())
                .enddate(backlog.getEnddate())
                .status(backlog.isStatus())
                .createdDate(backlog.getCreatedDate())
                .modifiedDate(backlog.getModifiedDate())
                .build();
        return backlogDto;
    }

    @Transactional
    public  BacklogDto getBacklog(Long backlogid){
        Optional<Backlog> backlog_wrapper = backlogRepository.findById(backlogid);
        Backlog backlog = backlog_wrapper.get();
        return  this.convertEntityToDto(backlog);
    }

    @Transactional
    public List<BacklogDto> getBacklogList(Sprint sprint) {
        List<Backlog> backlogList = backlogRepository.findBySprint(sprint);
        List<BacklogDto> backlogDtoList = new ArrayList<>();
        for (Backlog backlog : backlogList) {
            backlogDtoList.add(this.convertEntityToDto(backlog));
        }
        return backlogDtoList;
    }
    @Transactional
    public  Long saveBacklog (BacklogDto backlogDto) {

        return backlogRepository.save(backlogDto.toEntity()).getId();
    }
    @Transactional
    public void deleteBacklog(Long backlogid){
        BacklogDto backlogDto =  this.getBacklog(backlogid);
        Backlog backlog = backlogDto.toEntity();
        Sprint sprint = backlog.getSprint();
        sprint = sprint.removeBacklog(backlog);
        backlogRepository.deleteById(backlogid);
    }
}
