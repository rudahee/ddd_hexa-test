package net.jdazher.domain.tasks.service;


import net.jdazher.domain.tasks.model.Task;

import java.util.List;
import java.util.Optional;

/**
 * An interface representing a service for managing tasks in the system.
 *
 * <p>This interface defines the operations related to task management, such as retrieving,
 * saving, updating, and searching for tasks. It serves as a contract for implementing classes
 * that handle business logic associated with tasks.</p>
 */
public interface TaskService {

    /**
     * Retrieves a {@link Task} by its unique identifier.
     *
     * @param id The unique ID of the task to retrieve.
     * @return An {@link Optional} containing the task if found, or {@code Optional.empty()} if not found.
     */
    Optional<Task> getTaskById(String id);

    /**
     * Finds tasks whose title contains the given string.
     *
     * @param title A string to search for within task titles.
     * @return A list of tasks that contain the provided string in their title.
     */
    List<Task> findTasksByTitleContaining(String title);

    /**
     * Retrieves all tasks in the system.
     *
     * @return A list of all {@link Task} entities.
     */
    List<Task> getAllTasks();

    /**
     * Saves a new {@link Task} to the data store.
     *
     * @param task The task to save.
     * @return An {@link Optional} containing the saved task, or {@code Optional.empty()} if the save operation fails.
     */
    Optional<Task> saveTask(Task task);

    /**
     * Updates an existing {@link Task} in the data store.
     *
     * @param task The task to update.
     * @return An {@link Optional} containing the updated task, or {@code Optional.empty()} if the update fails.
     */
    Optional<Task> updateTask(Task task);

    /**
     * Saves a list of tasks to the data store.
     *
     * @param tasks A list of tasks to save.
     * @return A list of the saved {@link Task} entities.
     */
    List<Task> saveAll(List<Task> tasks);
}