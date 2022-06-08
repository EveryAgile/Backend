package org.everyagile.everyagile.repository;

import org.everyagile.everyagile.domain.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BacklogRepository extends JpaRepository<Backlog, Long> {
}
