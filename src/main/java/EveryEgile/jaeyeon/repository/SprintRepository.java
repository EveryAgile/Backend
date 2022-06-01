package EveryEgile.jaeyeon.repository;

import EveryEgile.jaeyeon.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import EveryEgile.jaeyeon.entity.Sprint;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SprintRepository extends JpaRepository<Sprint, Long> {
    List<Sprint> findByProject(@Param(value = "projectid") Project project); /// get Sprints list of that project
}
