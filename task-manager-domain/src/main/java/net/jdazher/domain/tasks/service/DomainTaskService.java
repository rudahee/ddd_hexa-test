package net.jdazher.domain.tasks.service;

import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TaskServuce {

    private TaskRepository repository;


    public TaskServuce(TaskRepository repository) {
        this.repository = repository;
    }


    public Optional<Task> getTaskById(UUID id) {
        return repository.getTaskById(id);
    }

    public Optional<Task> getTaskByTitle(String title) {
        return repository.getTaskByTitle(title);
    }

    public List<Task> getAllTasks() {
        return repository.getAllTasks();
    }

    public Optional<Task> saveTask(Task task) {
        return repository.saveTask(task);
    }

    public Optional<Task> updateTask(Task task) {
        return repository.updateTask(task);
    }

    public List<Task> saveAll(List<Task> tasks) {
        return repository.saveAll(tasks);
    }
}
