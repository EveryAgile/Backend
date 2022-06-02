package org.everyagile.everyagile.everyagile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.everyagile.everyagile.everyagile.entity.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByGroupid(Long Groupid);

}
