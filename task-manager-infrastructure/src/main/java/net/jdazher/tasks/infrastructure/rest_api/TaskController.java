package net.jdazher.tasks.infrastructure.rest_api;

import net.jdazher.domain.tasks.error.IllegalDateException;
import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.model.TaskTag;
import net.jdazher.tasks.infrastructure.rest_api.model.TaskJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskManagementRestApiAdapter taskAdapter;

    @Autowired
    public TaskController(TaskManagementRestApiAdapter taskAdapter) {
        this.taskAdapter = taskAdapter;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOne(@RequestBody final TaskJson task) throws IllegalDateException {
        return ResponseEntity.ok(taskAdapter.saveTask(new Task(task.getTitle(), task.getDescription(), task.getDueDate(), new ArrayList<>())));
    }

    @PostMapping("/create/all")
    public ResponseEntity<?> createAll(@RequestBody final List<Task> tasks) {
        return ResponseEntity.ok(taskAdapter.saveAll(tasks));
    }

    @PutMapping("/update")
    public ResponseEntity<?> UpdateOne(@RequestBody final Task task) {
        return ResponseEntity.ok(taskAdapter.updateTask(task));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOneById(@PathVariable final UUID id) {
        return ResponseEntity.ok(taskAdapter.getTaskById(id));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getOneByTitle(@RequestParam final String title) {
        return ResponseEntity.ok(taskAdapter.getTaskByTitle(title));
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(taskAdapter.getAllTasks());
    }



}