package net.jdazher.tasks.use_cases;

import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.service.TaskService;

import java.util.List;

public interface SaveAllTasksUseCase extends TaskService {

    List<Task> saveAll(List<Task> tasks);
}
