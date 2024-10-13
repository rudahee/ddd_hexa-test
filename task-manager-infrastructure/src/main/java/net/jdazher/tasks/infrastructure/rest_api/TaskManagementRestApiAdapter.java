package net.jdazher.tasks.infrastructure.rest_api;

import net.jdazher.domain.tasks.model.Task;
import net.jdazher.tasks.ports.input.GetTaskManagementInputPort;
import net.jdazher.tasks.ports.input.SaveOrUpdateTaskManagerInputPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskManagementRestApiAdapter implements GetTaskManagementInputPort, SaveOrUpdateTaskManagerInputPort {
    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    @Override
    public Optional<Task> getTaskById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<Task> getTaskByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public List<Task> saveAll(List<Task> tasks) {
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
}
