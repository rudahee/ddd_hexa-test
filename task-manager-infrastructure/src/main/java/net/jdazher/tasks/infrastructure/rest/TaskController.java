package net.jdazher.tasks.ports.input.rest;

import net.jdazher.domain.tasks.error.IllegalDateException;
import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.model.TaskTag;
import net.jdazher.domain.tasks.service.DomainTaskService;
import net.jdazher.tasks.ports.input.request.CreateTaskRequest;
import net.jdazher.tasks.ports.input.response.CreateErrorResponse;
import net.jdazher.tasks.ports.input.response.CreateTaskResponse;
import net.jdazher.tasks.ports.input.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final DomainTaskService taskService;

    @Autowired
    public TaskController(DomainTaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<? extends Response> createOne(@RequestBody final CreateTaskRequest taskRequest) {
        Optional<Task> taskOpt = taskService.saveTask(taskRequest.task());
        if (taskOpt.isPresent()) {
            return ResponseEntity.ok(new CreateTaskResponse(taskOpt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreateErrorResponse("Unable to create task"));
        }
    }

    @PostMapping("/create/all")
    public ResponseEntity<List<? extends Response>> createAll(@RequestBody final List<CreateTaskRequest> tasksRequests) {
        List<Task> tasks = tasksRequests.stream().map(CreateTaskRequest::task).toList();
        List<CreateTaskResponse> responses = taskService.saveAll(tasks).stream().map(CreateTaskResponse::new).toList();

        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update")
    public ResponseEntity<? extends Response> UpdateOne(@RequestBody final CreateTaskRequest taskRequest) {
        Optional<Task> taskOpt = taskService.saveTask(taskRequest.task());
        if (taskOpt.isPresent()) {
            return ResponseEntity.ok(new CreateTaskResponse(taskOpt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreateErrorResponse("Unable to create task"));
        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<? extends Response> getOneById(@PathVariable final UUID id) {
        Optional<Task> taskOpt = taskService.getTaskById(id);
        if (taskOpt.isPresent()) {
            return ResponseEntity.ok(new CreateTaskResponse(taskOpt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreateErrorResponse("Unable to create task"));
        }
    }

    @GetMapping("/get")
    public ResponseEntity<? extends Response> getOneByTitle(@RequestParam final String title) {
        Optional<Task> taskOpt = taskService.getTaskByTitle(title);
        if (taskOpt.isPresent()) {
            return ResponseEntity.ok(new CreateTaskResponse(taskOpt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreateErrorResponse("Unable to create task"));
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<? extends Response>> getAll() {
        List<CreateTaskResponse> responses = null;
        try {
            // Crear algunos TaskTags usando el método estático valueOf
            TaskTag tag1 = TaskTag.valueOf("Urgent");
            TaskTag tag2 = TaskTag.valueOf("Home");
            TaskTag tag3 = TaskTag.valueOf("Work");

            // Crear algunas fechas de vencimiento
            ZonedDateTime dueDate1 = ZonedDateTime.now().plusDays(5);
            ZonedDateTime dueDate2 = ZonedDateTime.now().plusMonths(1);
            ZonedDateTime dueDate3 = ZonedDateTime.now().plusWeeks(2);

            // Crear una lista de etiquetas para las tareas
            List<TaskTag> tagsForTask1 = List.of(tag1, tag2); // Ejemplo: "Urgent" y "Home"
            List<TaskTag> tagsForTask2 = List.of(tag3);       // Ejemplo: "Work"

            // Crear una lista de tareas (Task)
            List<Task> taskList = new ArrayList<>();

            // Crear y añadir las tareas a la lista
            taskList.add(new Task("Complete homework", "Math exercises", dueDate1, tagsForTask1));
            taskList.add(new Task("Team meeting", "Discuss project updates", dueDate2, tagsForTask2));
            taskList.add(new Task("Grocery shopping", "Buy food for the week", dueDate3)); // Sin etiquetas

            // Imprimir la lista de tareas
            taskList.forEach(System.out::println);

            responses = taskList.stream().map(CreateTaskResponse::new).toList();
        } catch (IllegalDateException e) {
            System.err.println("Error creating task: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error creating tag: " + e.getMessage());
        }


        return ResponseEntity.ok(responses);
    }

}