package EveryEgile.jaeyeon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import EveryEgile.jaeyeon.entity.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByGroupid(Long Groupid);

}
