package net.jdazher.tasks.infrastructure.rest_api;

import net.jdazher.domain.tasks.model.Task;
import net.jdazher.tasks.ports.input.GetTaskManagementInputPort;
import net.jdazher.tasks.ports.input.SaveOrUpdateTaskManagerInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class TaskController {


    private final GetTaskManagementInputPort getTaskService;
    private final SaveOrUpdateTaskManagerInputPort saveOrUpdateTaskService;

    @Autowired
    public TaskController(GetTaskManagementInputPort getTaskService, SaveOrUpdateTaskManagerInputPort saveOrUpdateTaskService) {
        this.getTaskService = getTaskService;
        this.saveOrUpdateTaskService = saveOrUpdateTaskService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createOne(@RequestBody final Task task) {
        return ResponseEntity.ok(saveOrUpdateTaskService.saveTask(task));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAll(@RequestBody final List<Task> tasks) {
        return ResponseEntity.ok(saveOrUpdateTaskService.saveAll(tasks));
    }

    @PutMapping("/update")
    public ResponseEntity<?> createAll(@RequestBody final Task task) {
        return ResponseEntity.ok(saveOrUpdateTaskService.updateTask(task));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOneById(@PathVariable final UUID id) {
        return ResponseEntity.ok(getTaskService.getTaskById(id));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getOneByTitle(@RequestParam final String title) {
        return ResponseEntity.ok(getTaskService.getTaskByTitle(title));
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getOneByTitle() {
        return ResponseEntity.ok(getTaskService.getAllTasks());
    }

}