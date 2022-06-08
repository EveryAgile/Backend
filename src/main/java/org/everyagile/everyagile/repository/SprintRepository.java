package org.everyagile.everyagile.repository;

import org.everyagile.everyagile.domain.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint, Long> {
}
