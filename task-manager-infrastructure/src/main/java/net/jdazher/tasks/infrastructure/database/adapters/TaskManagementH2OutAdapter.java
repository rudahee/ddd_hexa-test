package net.jdazher.tasks.infrastructure.database.adapters;

import net.jdazher.domain.tasks.error.IllegalDateException;
import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.model.TaskStatus;
import net.jdazher.domain.tasks.model.TaskTag;
import net.jdazher.tasks.infrastructure.database.entity.TaskEntity;
import net.jdazher.tasks.infrastructure.database.entity.TaskStatusEntity;
import net.jdazher.tasks.infrastructure.database.repositories.TaskH2Repository;
import net.jdazher.tasks.ports.output.TaskManagementOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TaskManagementH2OutAdapter implements TaskManagementOutputPort {

    private final TaskH2Repository repository;

    @Autowired
    public TaskManagementH2OutAdapter(TaskH2Repository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Task> getTaskById(UUID id) {
        Optional<TaskEntity> opt = repository.findById(id);

        return opt.map(this::fromEntityToTask);
    }

    @Override
    public Optional<Task> getTaskByTitle(String title) {
        Optional<TaskEntity> opt = repository.findByTitle(title);

        return opt.map(this::fromEntityToTask);
    }

    @Override
    public List<Task> getAllTasks() {
        return fromEntitiesToTasks(repository.findAll());
    }

    @Override
    public Optional<Task> saveTask(Task task) {
        TaskEntity entity = fromTaskToEntity(task);
        return Optional.ofNullable(fromEntityToTask(repository.save(entity)));
    }

    @Override
    public Optional<Task> updateTask(Task task) {
        TaskEntity entity = fromTaskToEntity(task);
        return Optional.ofNullable(fromEntityToTask(repository.save(entity)));
    }

    @Override
    public List<Task> saveAll(List<Task> tasks) {
        List<TaskEntity> entities = fromTasksToEntities(tasks);
        return fromEntitiesToTasks(repository.saveAll(entities));
    }

    @Override
    public String transformTagToString(TaskTag tag) {
        return tag.getTag().toString();
    }

    @Override
    public TaskTag transformStringToTag(String tag) {
        return TaskTag.valueOf(tag);
    }


    private List<Task> fromEntitiesToTasks(List<TaskEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream().map(entity -> fromEntityToTask(entity)).toList();
    }

    private Task fromEntityToTask(TaskEntity entity) {

        Task task = null;

        try {
            TaskStatus status = TaskStatus.valueOf(entity.getStatus().name());

            task = new Task(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getDueDate(), status, transformStringsToTags(entity.getTags()));
        } catch (IllegalDateException e) {
            e.printStackTrace();
        }
        return task;
    }

    private List<TaskEntity> fromTasksToEntities(List<Task> tasks) {

        if (tasks == null) {
            return null;
        }
        return tasks.stream().map(entity -> fromTaskToEntity(entity)).toList();
    }

    private TaskEntity fromTaskToEntity(Task entity) {

        TaskStatusEntity status = TaskStatusEntity.valueOf(entity.getStatus().name());

        return new TaskEntity(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getDueDate(), status, transformTagsToStrings(entity.getTags()));
    }

}
