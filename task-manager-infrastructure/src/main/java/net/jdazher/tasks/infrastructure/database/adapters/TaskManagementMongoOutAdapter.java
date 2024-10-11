package net.jdazher.tasks.infrastructure.adapters;

import net.jdazher.domain.tasks.model.Task;
import net.jdazher.tasks.ports.output.TaskManagementOutputPort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TaskManagementMongoOutAdapter implements TaskManagementOutputPort {
    @Override
    public Optional<Task> getTaskById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<Task> getTaskByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    @Override
    public Optional<Task> saveTask(Task task) {
        return Optional.empty();
    }

    @Override
    public Optional<Task> updateTask(Task task) {
        return Optional.empty();
    }

    @Override
    public List<Task> saveAll(List<Task> tasks) {
        return null;
    }
}
