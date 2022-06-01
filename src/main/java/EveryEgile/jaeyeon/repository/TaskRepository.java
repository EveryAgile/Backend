package EveryEgile.jaeyeon.repository;

import EveryEgile.jaeyeon.entity.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import EveryEgile.jaeyeon.entity.Task;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByBacklog(@Param(value = "sprintid") Backlog backlog);

}