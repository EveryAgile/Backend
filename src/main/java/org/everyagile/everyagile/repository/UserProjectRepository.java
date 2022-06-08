package org.everyagile.everyagile.repository;

import org.everyagile.everyagile.domain.Project;
import org.everyagile.everyagile.domain.User;
import org.everyagile.everyagile.domain.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProjectRepository extends JpaRepository<UserProject, Long> {
    List<UserProject> findAllByUser(User user);
    List<UserProject> findAllByProject(Project project);
    void deleteAllByProject(Project project);
}
