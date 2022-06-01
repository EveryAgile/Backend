package EveryEgile.jaeyeon.repository;

import EveryEgile.jaeyeon.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import EveryEgile.jaeyeon.entity.Backlog;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BacklogRepository extends JpaRepository<Backlog, Long> {
    List<Backlog> findBySprint(@Param(value = "sprintid") Sprint sprint);

}
