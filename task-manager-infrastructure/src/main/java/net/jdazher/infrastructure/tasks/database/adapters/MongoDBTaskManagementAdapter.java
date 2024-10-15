package net.jdazher.infrastructure.tasks.database.adapters;

import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.repository.TaskRepository;
import net.jdazher.infrastructure.tasks.database.repositories.SpringDataMongoTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


/**
 * Adapter class for managing tasks in a MongoDB data source.
 *
 * <p>This class implements the {@link TaskRepository} interface, providing concrete
 * methods for task management using Spring Data MongoDB.</p>
 *
 * <p>It acts as a bridge between the application's task management logic and the MongoDB
 * data store, facilitating operations such as retrieving, saving, and updating tasks.</p>
 */
@Component
public class MongoDBTaskManagementAdapter implements TaskRepository {

    private final SpringDataMongoTaskRepository repository;

    /**
     * Constructs a new {@code MongoDBTaskManagementAdapter} with the specified
     * {@link SpringDataMongoTaskRepository}.
     *
     * @param repository The Spring Data MongoDB repository for accessing task data.
     */
    @Autowired
    public MongoDBTaskManagementAdapter(SpringDataMongoTaskRepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Task> getTaskById(String id) {
        return repository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Task> findTasksByTitleContaining(String title) {
        return repository.findTasksByTitleContaining(title);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Task> saveTask(Task task) {
        return Optional.of(repository.save(task));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Task> updateTask(Task task) {
        return Optional.of(repository.save(task));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Task> saveAll(List<Task> tasks) {
        return repository.saveAll(tasks);
    }
}
