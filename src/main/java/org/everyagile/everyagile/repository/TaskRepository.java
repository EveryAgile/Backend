package org.everyagile.everyagile.repository;

import org.everyagile.everyagile.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
