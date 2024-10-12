package net.jdazher.tasks.use_cases;

import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.service.TaskService;

import java.util.Optional;

public interface SaveTaskUseCase extends TaskService {

    Optional<Task> saveTask(Task task);
}
