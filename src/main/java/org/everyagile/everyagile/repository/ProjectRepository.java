package org.everyagile.everyagile.repository;

import org.everyagile.everyagile.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
