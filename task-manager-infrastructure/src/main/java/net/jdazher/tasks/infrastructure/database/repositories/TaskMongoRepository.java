package net.jdazher.tasks.infrastructure.database.repositories;

import net.jdazher.domain.tasks.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskMongoRepository extends MongoRepository<Task, UUID> {


    Optional<Task> findByTitle(String title);
}
