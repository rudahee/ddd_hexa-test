package net.jdazher.domain.tasks.service;

import net.jdazher.domain.tasks.error.IllegalDateException;
import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DomainTaskServiceTest {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private DomainTaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Crear un task de prueba
        task = new Task("Test Task", "This is a test task",
                LocalDateTime.now().plusDays(5),
                Arrays.asList());
    }

    @Test
    void testGetTaskByIdWhenTaskExists() {
        String taskId = "1234";
        when(repository.getTaskById(taskId)).thenReturn(Optional.of(task));

        Optional<Task> foundTask = taskService.getTaskById(taskId);

        assertTrue(foundTask.isPresent());
        assertEquals("Test Task", foundTask.get().getTitle());
        verify(repository, times(1)).getTaskById(taskId);
    }

    @Test
    void testGetTaskByIdWhenTaskDoesNotExist() {
        String taskId = "1234";
        when(repository.getTaskById(taskId)).thenReturn(Optional.empty());

        Optional<Task> foundTask = taskService.getTaskById(taskId);

        assertFalse(foundTask.isPresent());
        verify(repository, times(1)).getTaskById(taskId);
    }

    @Test
    void testFindTasksByTitleContaining() {
        String titleFragment = "Test";
        List<Task> tasks = Arrays.asList(task);
        when(repository.findTasksByTitleContaining(titleFragment)).thenReturn(tasks);

        List<Task> foundTasks = taskService.findTasksByTitleContaining(titleFragment);

        assertEquals(1, foundTasks.size());
        assertEquals("Test Task", foundTasks.get(0).getTitle());
        verify(repository, times(1)).findTasksByTitleContaining(titleFragment);
    }

    @Test
    void testGetAllTasks() {
        List<Task> tasks = Arrays.asList(task);
        when(repository.getAllTasks()).thenReturn(tasks);

        List<Task> foundTasks = taskService.getAllTasks();

        assertEquals(1, foundTasks.size());
        verify(repository, times(1)).getAllTasks();
    }

    @Test
    void testSaveTask() {
        when(repository.saveTask(any(Task.class))).thenReturn(Optional.of(task));

        Optional<Task> savedTask = taskService.saveTask(task);

        assertTrue(savedTask.isPresent());
        assertEquals("Test Task", savedTask.get().getTitle());
        verify(repository, times(1)).saveTask(task);
    }

    @Test
    void testUpdateTask() {
        try {
            Task updatedTask = new Task("Updated Task", "Updated description",
                    LocalDateTime.now().plusDays(10), Arrays.asList());
            when(repository.updateTask(any(Task.class))).thenReturn(Optional.of(updatedTask));

            Optional<Task> result = taskService.updateTask(updatedTask);

            assertTrue(result.isPresent());
            assertEquals("Updated Task", result.get().getTitle());
            verify(repository, times(1)).updateTask(updatedTask);
        } catch (IllegalDateException e) {
            fail(e.getMessage());
        }

    }

    @Test
    void testSaveAllTasks() {
        List<Task> tasks = Arrays.asList(task);
        when(repository.saveAll(tasks)).thenReturn(tasks);

        List<Task> savedTasks = taskService.saveAll(tasks);

        assertEquals(1, savedTasks.size());
        verify(repository, times(1)).saveAll(tasks);
    }
}
