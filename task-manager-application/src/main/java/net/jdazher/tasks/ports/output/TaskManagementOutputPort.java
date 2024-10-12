package net.jdazher.tasks.ports.output;

import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskManagementOutputPort extends TaskRepository {

    Optional<Task> getTaskById(UUID id);
    Optional<Task> getTaskByTitle(String title);
    List<Task> getAllTasks();
    Optional<Task> saveTask(Task task);
    Optional<Task> updateTask(Task task);
    List<Task> saveAll(List<Task> tasks);
}
