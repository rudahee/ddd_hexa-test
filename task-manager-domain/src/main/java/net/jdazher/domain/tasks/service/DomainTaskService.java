package net.jdazher.domain.tasks.service;

import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link TaskService} interface for managing tasks in the domain layer.
 *
 * <p>This class provides concrete implementations for the task management methods defined
 * in the {@link TaskService} interface, delegating operations to a {@link TaskRepository}
 * instance for data access.</p>
 *
 * <p>It serves as a bridge between the application's business logic and the underlying
 * data storage, facilitating operations such as retrieving, saving, and updating tasks.</p>
 */
public class DomainTaskService implements TaskService {

    private final TaskRepository repository;

    /**
     * Constructs a new {@code DomainTaskService} with the specified {@link TaskRepository}.
     *
     * @param repository The repository used for accessing task data.
     */
    public DomainTaskService(TaskRepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Task> getTaskById(String id) {
        return repository.getTaskById(id);
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
        return repository.getAllTasks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Task> saveTask(Task task) {
        return repository.saveTask(task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Task> updateTask(Task task) {
        return repository.updateTask(task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Task> saveAll(List<Task> tasks) {
        return repository.saveAll(tasks);
    }
}