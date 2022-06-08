package org.everyagile.everyagile.repository;

import org.everyagile.everyagile.domain.Sprint;
import org.everyagile.everyagile.domain.User;
import org.everyagile.everyagile.domain.UserSprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSprintRepository extends JpaRepository<UserSprint, Long> {
    void deleteAllBySprint(Sprint sprint);
    List<UserSprint> findAllByUser(User user);
    List<UserSprint> findAllBySprint(Sprint sprint);
}
