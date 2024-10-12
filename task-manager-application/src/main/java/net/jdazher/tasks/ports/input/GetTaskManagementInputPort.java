package net.jdazher.tasks.ports.input;


import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.repository.TaskRepository;
import net.jdazher.tasks.ports.output.TaskManagementOutputPort;
import net.jdazher.tasks.use_cases.GetAllTasksUseCase;
import net.jdazher.tasks.use_cases.GetTaskUseCase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GetTaskManagementInputPort implements GetAllTasksUseCase, GetTaskUseCase {

    private final TaskRepository taskRepository;

    public GetTaskManagementInputPort(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    @Override
    public Optional<Task> getTaskById(UUID id) {
        return taskRepository.findById(id);
    }
    @Override
    public Optional<Task> getTaskByTitle(String title) {
        return taskRepository.findByTitle(title);
    }
}
