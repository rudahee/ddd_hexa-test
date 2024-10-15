package net.jdazher.infrastructure.tasks.rest;

import lombok.extern.log4j.Log4j2;
import net.jdazher.domain.tasks.error.IllegalDateException;
import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.model.TaskStatus;
import net.jdazher.domain.tasks.service.DomainTaskService;
import net.jdazher.domain.tasks.service.TaskService;
import net.jdazher.application.tasks.request.CreateTaskRequest;
import net.jdazher.application.tasks.response.CreateErrorResponse;
import net.jdazher.application.tasks.response.CreateTaskResponse;
import net.jdazher.application.tasks.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing tasks.
 *
 * <p>This class provides endpoints for creating, updating, and retrieving tasks.</p>
 *
 * <p>Endpoints include:</p>
 * <ul>
 *     <li>POST /tasks/create: Create a new task.</li>
 *     <li>POST /tasks/create/all: Create multiple tasks.</li>
 *     <li>PUT /tasks/update/{id}: Update the status of a task.</li>
 *     <li>GET /tasks/get/{id}: Retrieve a task by its ID.</li>
 *     <li>GET /tasks/get: Retrieve tasks by title.</li>
 *     <li>GET /tasks/get/all: Retrieve all tasks.</li>
 * </ul>
 */
@RestController
@RequestMapping("/tasks")
@Log4j2
public class TaskController {

    private final DomainTaskService taskService;

    /**
     * Constructs a new {@code TaskController} with the specified {@link DomainTaskService}.
     *
     * @param taskService The service used for task management.
     */
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = (DomainTaskService) taskService;
    }

    /**
     * Creates a new task based on the provided request.
     *
     * @param taskRequest The request containing task details.
     * @return A response entity containing the created task or an error message.
     * @throws IllegalDateException If the provided due date is invalid.
     */
    @PostMapping("/create")
    public ResponseEntity<? extends Response> createOne(@RequestBody final CreateTaskRequest taskRequest) throws IllegalDateException {
        log.info("Started create task");
        Task task = taskRequest.getTask();
        Task finalTask = new Task(task.getTitle(), task.getDescription(), task.getDueDate(), task.getTags());

        Optional<Task> taskOpt = taskService.saveTask(finalTask);
        if (taskOpt.isPresent()) {
            log.info("Finished create task");
            return ResponseEntity.ok(new CreateTaskResponse(finalTask));
        } else {
            log.error("Failed to create task");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreateErrorResponse("Unable to create task"));
        }
    }

    /**
     * Creates multiple tasks based on the provided list of task requests.
     *
     * @param tasksRequests The list of requests containing task details.
     * @return A response entity containing the list of created tasks.
     */
    @PostMapping("/create/all")
    public ResponseEntity<List<? extends Response>> createAll(@RequestBody final List<CreateTaskRequest> tasksRequests) {
        log.info("Started create a bunch of tasks");
        List<Task> tasks = tasksRequests.stream().map(CreateTaskRequest::getTask).toList();

        List<CreateTaskResponse> responses = taskService.saveAll(tasks).stream().map(CreateTaskResponse::new).toList();

        log.info("Finished create a bunch of tasks. Created " + responses.size() + " tasks");
        return ResponseEntity.ok(responses);
    }

    /**
     * Updates the status of a task identified by the given ID.
     *
     * @param taskStatus The new status for the task.
     * @param id        The ID of the task to update.
     * @return A response entity containing the updated task or an error message.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<? extends Response> updateStatus(@RequestParam("status") final TaskStatus taskStatus, @PathVariable final String id) {
        log.info("Updating task " + id + " to status " + taskStatus);
        Optional<Task> opt = taskService.getTaskById(id);

        if (opt.isPresent()) {
            Task task = opt.get();

            task.changeStatus(taskStatus);
            Optional<Task> returnedTask = taskService.updateTask(task);
            if (returnedTask.isPresent()) {
                log.info("Finished updating task " + id + " to status " + taskStatus);
                return ResponseEntity.ok(new CreateTaskResponse(returnedTask.get()));
            }
        }
        log.info("Failed to update task " + id + " to status " + taskStatus + " due to an error in the api");
        return ResponseEntity.ok(new CreateErrorResponse("Error updating task"));
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param id The ID of the task to retrieve.
     * @return A response entity containing the requested task or an error message.
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<? extends Response> getOneById(@PathVariable final String id) {
        log.info("Getting task " + id);
        Optional<Task> taskOpt = taskService.getTaskById(id);
        if (taskOpt.isPresent()) {
            log.info("Finished getting task " + id);
            return ResponseEntity.ok(new CreateTaskResponse(taskOpt.get()));
        } else {
            log.error("Failed to get task " + id + " due to an error in the api");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreateErrorResponse("Unable to create task"));
        }
    }

    /**
     * Retrieves tasks containing the specified title.
     *
     * @param title The title to search for in tasks.
     * @return A response entity containing the list of tasks with the specified title.
     */
    @GetMapping("/get")
    public ResponseEntity<List<? extends Response>> getByTitle(@RequestParam final String title) {
        log.info("Getting tasks with title: " + title);
        List<CreateTaskResponse> responses = taskService.findTasksByTitleContaining(title).stream().map(CreateTaskResponse::new).toList();

        log.info("Finished getting tasks with title: " + title + ". Total found: " + responses.size());
        return ResponseEntity.ok(responses);
    }

    /**
     * Retrieves all tasks.
     *
     * @return A response entity containing the list of all tasks.
     */
    @GetMapping("/get/all")
    public ResponseEntity<List<? extends Response>> getAll() {
        log.info("Getting all tasks");
        List<Task> tasks = taskService.getAllTasks();
        List<CreateTaskResponse> responses = tasks.stream().map(CreateTaskResponse::new).toList();

        log.info("Finished getting all tasks. Total found: " + responses.size());
        return ResponseEntity.ok(responses);
    }
}
