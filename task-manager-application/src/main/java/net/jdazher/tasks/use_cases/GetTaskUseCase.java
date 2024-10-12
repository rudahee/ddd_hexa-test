package net.jdazher.tasks.use_cases;

import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.service.TaskService;

import java.util.Optional;
import java.util.UUID;

public interface GetTaskUseCase extends TaskService {
    Optional<Task> getTaskById(UUID id);
    Optional<Task> getTaskByTitle(String title);
}
