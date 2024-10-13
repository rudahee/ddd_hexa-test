package net.jdazher.tasks.infrastructure.database.adapters;

import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.model.TaskTag;
import net.jdazher.tasks.infrastructure.database.repositories.TaskMongoRepository;
import net.jdazher.tasks.ports.output.TaskManagementOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TaskManagementMongoOutAdapter implements TaskManagementOutputPort {

    private final TaskMongoRepository repository;

    @Autowired
    public TaskManagementMongoOutAdapter(TaskMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Task> getTaskById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Task> getTaskByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    @Override
    public Optional<Task> saveTask(Task task) {
        return Optional.of(repository.save(task));
    }

    @Override
    public Optional<Task> updateTask(Task task) {
        return Optional.of(repository.save(task));
    }

    @Override
    public List<Task> saveAll(List<Task> tasks) {
        return repository.saveAll(tasks);
    }

    @Override
    public TaskTag transformStringToTag(String tag) {
        return null;
    }

    @Override
    public List<TaskTag> transformStringsToTags(List<String> tags) {
        return null;
    }

    @Override
    public String transformTagToString(TaskTag tag) {
        return null;
    }

    @Override
    public List<String> transformTagsToStrings(List<TaskTag> tags) {
        return TaskManagementOutputPort.super.transformTagsToStrings(tags);
    }
}
