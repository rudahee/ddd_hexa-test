package net.jdazher.tasks.infrastructure.rest;

import net.jdazher.domain.tasks.error.IllegalDateException;
import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.model.TaskStatus;
import net.jdazher.domain.tasks.service.DomainTaskService;
import net.jdazher.application.tasks.request.CreateTaskRequest;
import net.jdazher.application.tasks.response.CreateErrorResponse;
import net.jdazher.application.tasks.response.CreateTaskResponse;
import net.jdazher.application.tasks.response.Response;
import net.jdazher.infrastructure.tasks.rest.TaskController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskControllerTest {


    @Mock
    private DomainTaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializar mocks
    }

    @Test
    void testCreateOneSuccess() throws IllegalDateException {
        Task task = new Task("Test Title", "Test Description", LocalDateTime.now().plusDays(1));
        CreateTaskRequest request = new CreateTaskRequest(task);
        when(taskService.saveTask(any(Task.class))).thenReturn(Optional.of(task));

        ResponseEntity<? extends Response> response = taskController.createOne(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testCreateOneFailure() throws IllegalDateException {
        Task task = new Task("Test Title", "Test Description", LocalDateTime.now().plusDays(1));
        CreateTaskRequest request = new CreateTaskRequest(task);
        when(taskService.saveTask(any(Task.class))).thenReturn(Optional.empty());

        ResponseEntity<? extends Response> response = taskController.createOne(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Unable to create task", ((CreateErrorResponse) response.getBody()).getMessage());
        verify(taskService, times(1)).saveTask(any(Task.class));
    }

    @Test
    void testCreateAllSuccess() throws IllegalDateException {
        Task task1 = new Task("Task 1", "Description 1", LocalDateTime.now().plusDays(1));
        Task task2 = new Task("Task 2", "Description 2", LocalDateTime.now().plusDays(2));
        List<CreateTaskRequest> requests = List.of(new CreateTaskRequest(task1), new CreateTaskRequest(task2));

        when(taskService.saveAll(anyList())).thenReturn(List.of(task1, task2));

        ResponseEntity<List<? extends Response>> response = taskController.createAll(requests);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(taskService, times(1)).saveAll(anyList());
    }

    @Test
    void testUpdateStatusSuccess() throws IllegalDateException {
        Task task = new Task("Test Title", "Test Description", LocalDateTime.now().plusDays(1));
        when(taskService.getTaskById("1")).thenReturn(Optional.of(task));
        when(taskService.updateTask(any(Task.class))).thenReturn(Optional.of(task));

        ResponseEntity<? extends Response> response = taskController.updateStatus(TaskStatus.IN_PROGRESS, "1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(taskService, times(1)).getTaskById("1");
        verify(taskService, times(1)).updateTask(task);
    }

    @Test
    void testUpdateStatusFailure() {
        when(taskService.getTaskById("1")).thenReturn(Optional.empty());

        ResponseEntity<? extends Response> response = taskController.updateStatus(TaskStatus.IN_PROGRESS, "1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Error updating task", ((CreateErrorResponse) response.getBody()).getMessage());
        verify(taskService, times(1)).getTaskById("1");
        verify(taskService, never()).updateTask(any(Task.class));
    }

    @Test
    void testGetOneByIdSuccess() throws IllegalDateException {
        Task task = new Task("Test Title", "Test Description", LocalDateTime.now().plusDays(1));
        when(taskService.getTaskById("1")).thenReturn(Optional.of(task));

        ResponseEntity<? extends Response> response = taskController.getOneById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task, ((CreateTaskResponse) response.getBody()).getTask());
        verify(taskService, times(1)).getTaskById("1");
    }

    @Test
    void testGetOneByIdFailure() {
        when(taskService.getTaskById("1")).thenReturn(Optional.empty());

        ResponseEntity<? extends Response> response = taskController.getOneById("1");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Unable to create task", ((CreateErrorResponse) response.getBody()).getMessage());
        verify(taskService, times(1)).getTaskById("1");
    }

    @Test
    void testGetByTitle() throws IllegalDateException {
        Task task1 = new Task("Test Title 1", "Description 1", LocalDateTime.now().plusDays(1));
        Task task2 = new Task("Test Title 2", "Description 2", LocalDateTime.now().plusDays(2));

        when(taskService.findTasksByTitleContaining("Test")).thenReturn(List.of(task1, task2));

        ResponseEntity<List<? extends Response>> response = taskController.getByTitle("Test");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(taskService, times(1)).findTasksByTitleContaining("Test");
    }

    @Test
    void testGetAll() throws IllegalDateException {
        Task task1 = new Task("Test Title 1", "Description 1", LocalDateTime.now().plusDays(1));
        Task task2 = new Task("Test Title 2", "Description 2", LocalDateTime.now().plusDays(2));

        when(taskService.getAllTasks()).thenReturn(List.of(task1, task2));

        ResponseEntity<List<? extends Response>> response = taskController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(taskService, times(1)).getAllTasks();
    }

}