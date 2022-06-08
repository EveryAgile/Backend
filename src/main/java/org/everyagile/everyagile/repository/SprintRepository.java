package org.everyagile.everyagile.repository;

import org.everyagile.everyagile.domain.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SprintRepository extends JpaRepository<Sprint, Long> {
    Optional<Sprint> findById(Long sprintId);
}
