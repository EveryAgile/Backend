package EveryEgile.jaeyeon.service;

import EveryEgile.jaeyeon.entity.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import EveryEgile.jaeyeon.entity.Sprint;
import EveryEgile.jaeyeon.dto.SprintDto;
import EveryEgile.jaeyeon.repository.SprintRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SprintService {

    private SprintRepository sprintRepository;

    @Transactional
    private SprintDto convertEntityToDto(Sprint sprint) {
        SprintDto sprintDto = SprintDto.builder()
                .id(sprint.getId())
                .project(sprint.getProject())
                .description(sprint.getDescription())
                .importance(sprint.getImportance())
                .name(sprint.getName())
                .enddate(sprint.getEnddate())
                .status(sprint.isStatus())
                .createdDate(sprint.getCreatedDate())
                .modifiedDate(sprint.getModifiedDate())
                .build();
        return sprintDto;
    }

    @Transactional
    public SprintDto getSprint(Long sprintid) {

        Optional<Sprint> sprint_wrapper = sprintRepository.findById(sprintid);
        Sprint sprint = sprint_wrapper.get();
        return this.convertEntityToDto(sprint);
    }
    @Transactional
    public List<SprintDto> getSprintList(Project Project) {
        List<Sprint> sprint_list = sprintRepository.findByProject(Project);
        List<SprintDto> sprint_Dto_list = new ArrayList<>();
        for (Sprint sprint : sprint_list) {
            sprint_Dto_list.add(this.convertEntityToDto(sprint));
        }
        return sprint_Dto_list;
    }



    @Transactional
    public Long saveSprint(SprintDto sprintDto) {
        return sprintRepository.save(sprintDto.toEntity()).getId();
    }

    @Transactional
    public void deleteSprint(Long sprintid){
        SprintDto sprintDto =this.getSprint(sprintid);
        Sprint sprint = sprintDto.toEntity();
        Project project = sprint.getProject();
        project.removeSprint(sprint);
        sprintRepository.deleteById(sprintid);
    }

    //@Transactional
//      public  getGroupIdList (Long Project_id) {
//    }




}
