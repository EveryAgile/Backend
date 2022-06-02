package org.everyagile.everyagile.everyagile.repository;

import org.everyagile.everyagile.everyagile.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.everyagile.everyagile.everyagile.entity.Backlog;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BacklogRepository extends JpaRepository<Backlog, Long> {
    List<Backlog> findBySprint(@Param(value = "sprintid") Sprint sprint);

}
