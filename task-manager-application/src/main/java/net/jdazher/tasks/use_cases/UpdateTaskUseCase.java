package net.jdazher.tasks.use_cases;

import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.service.TaskService;

import java.util.Optional;

public interface UpdateTaskUseCase extends TaskService {

    Optional<Task> updateTask(Task task);
}
