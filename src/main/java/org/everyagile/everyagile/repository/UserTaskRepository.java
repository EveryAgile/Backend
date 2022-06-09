package org.everyagile.everyagile.repository;

import org.everyagile.everyagile.domain.Task;
import org.everyagile.everyagile.domain.User;
import org.everyagile.everyagile.domain.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
    void deleteAllByTask(Task task);
    List<UserTask> findAllByUser(User user);
    List<UserTask> findAllByTask(Task task);
}
