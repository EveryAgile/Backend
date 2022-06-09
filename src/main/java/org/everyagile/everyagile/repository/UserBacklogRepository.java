package org.everyagile.everyagile.repository;

import org.everyagile.everyagile.domain.Backlog;
import org.everyagile.everyagile.domain.User;
import org.everyagile.everyagile.domain.UserBacklog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBacklogRepository extends JpaRepository<UserBacklog, Long> {
    void deleteAllByBacklog(Backlog backlog);
    List<UserBacklog> findAllByUser(User user);
    List<UserBacklog> findAllByBacklog(Backlog backlog);
}
