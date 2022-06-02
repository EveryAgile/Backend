package org.everyagile.everyagile.everyagile.repository;

import org.everyagile.everyagile.everyagile.entity.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.everyagile.everyagile.everyagile.entity.Task;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByBacklog(@Param(value = "sprintid") Backlog backlog);

}