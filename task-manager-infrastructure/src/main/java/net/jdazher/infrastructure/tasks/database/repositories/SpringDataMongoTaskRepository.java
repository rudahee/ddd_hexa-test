package net.jdazher.infrastructure.tasks.database.repositories;

import net.jdazher.domain.tasks.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing {@link Task} entities in MongoDB.
 *
 * <p>This interface extends {@link MongoRepository}, providing standard CRUD operations
 * for {@link Task} entities along with custom query methods.</p>
 *
 * <p>It includes a method for finding tasks based on a title search, leveraging
 * Spring Data MongoDB's query derivation capabilities.</p>
 */
@Repository
public interface SpringDataMongoTaskRepository extends MongoRepository<Task, String> {

    /**
     * Finds all tasks whose title contains the specified string.
     *
     * @param title A string to search for within task titles.
     * @return A list of tasks that contain the specified string in their title.
     */
    List<Task> findTasksByTitleContaining(String title);
}
