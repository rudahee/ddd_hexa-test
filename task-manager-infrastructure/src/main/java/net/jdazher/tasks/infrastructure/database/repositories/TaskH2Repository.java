package net.jdazher.tasks.infrastructure.database.repositories;

import net.jdazher.tasks.infrastructure.database.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskH2Repository extends JpaRepository<TaskEntity, UUID> {

    Optional<TaskEntity> findByTitle(String title);
}
