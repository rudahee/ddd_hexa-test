package net.jdazher.tasks.ports.input;

import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.repository.TaskRepository;
import net.jdazher.tasks.use_cases.SaveAllTasksUseCase;
import net.jdazher.tasks.use_cases.SaveTaskUseCase;
import net.jdazher.tasks.use_cases.UpdateTaskUseCase;

import java.util.List;
import java.util.Optional;

public class SaveOrUpdateTaskManagerInputPort implements SaveTaskUseCase, SaveAllTasksUseCase, UpdateTaskUseCase {

    private final TaskRepository taskRepository;

    public SaveOrUpdateTaskManagerInputPort(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> saveAll(List<Task> tasks) {
        return taskRepository.saveAll(tasks);
    }
    @Override
    public Optional<Task> saveTask(Task task) {
        return Optional.of(taskRepository.save(task));
    }
    @Override
    public Optional<Task> updateTask(Task task) {
        return  Optional.of(taskRepository.update(task));
    }
}
