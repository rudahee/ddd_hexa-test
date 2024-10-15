package net.jdazher.tasks.infrastructure.database.adapters;

import net.jdazher.domain.tasks.error.IllegalDateException;
import net.jdazher.domain.tasks.model.Task;
import net.jdazher.infrastructure.tasks.database.adapters.MongoDBTaskManagementAdapter;
import net.jdazher.infrastructure.tasks.database.repositories.SpringDataMongoTaskRepository;
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

class MongoDBTaskManagementAdapterTest {


    @Mock
    private SpringDataMongoTaskRepository repository;

    @InjectMocks
    private MongoDBTaskManagementAdapter taskManagementAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializar mocks
    }

    @Test
    void testGetTaskById_Success() throws IllegalDateException {
        // Datos de prueba
        String taskId = "123";
        Task task = new Task("Test Task", "Task Description", LocalDateTime.now().plusDays(1));

        // Simular comportamiento del repositorio
        when(repository.findById(taskId)).thenReturn(Optional.of(task));

        // Invocar el método
        Optional<Task> result = taskManagementAdapter.getTaskById(taskId);

        // Verificaciones
        assertTrue(result.isPresent());
        assertEquals(task, result.get());
        verify(repository, times(1)).findById(taskId);
    }

    @Test
    void testGetTaskById_NotFound() {
        // Datos de prueba
        String taskId = "123";

        // Simular comportamiento del repositorio
        when(repository.findById(taskId)).thenReturn(Optional.empty());

        // Invocar el método
        Optional<Task> result = taskManagementAdapter.getTaskById(taskId);

        // Verificaciones
        assertFalse(result.isPresent());
        verify(repository, times(1)).findById(taskId);
    }

    @Test
    void testFindTasksByTitleContaining() throws IllegalDateException {
        // Datos de prueba
        String title = "Test";
        Task task1 = new Task("Test Task 1", "Description 1", LocalDateTime.now().plusDays(1));
        Task task2 = new Task("Test Task 2", "Description 2", LocalDateTime.now().plusDays(1));
        List<Task> tasks = Arrays.asList(task1, task2);

        // Simular comportamiento del repositorio
        when(repository.findTasksByTitleContaining(title)).thenReturn(tasks);

        // Invocar el método
        List<Task> result = taskManagementAdapter.findTasksByTitleContaining(title);

        // Verificaciones
        assertEquals(2, result.size());
        assertTrue(result.contains(task1));
        assertTrue(result.contains(task2));
        verify(repository, times(1)).findTasksByTitleContaining(title);
    }

    @Test
    void testGetAllTasks() throws IllegalDateException {
        // Datos de prueba
        Task task1 = new Task("Task 1", "Description 1", LocalDateTime.now().plusDays(1));
        Task task2 = new Task("Task 2", "Description 2", LocalDateTime.now().plusDays(1));
        List<Task> tasks = Arrays.asList(task1, task2);

        // Simular comportamiento del repositorio
        when(repository.findAll()).thenReturn(tasks);

        // Invocar el método
        List<Task> result = taskManagementAdapter.getAllTasks();

        // Verificaciones
        assertEquals(2, result.size());
        assertTrue(result.contains(task1));
        assertTrue(result.contains(task2));
        verify(repository, times(1)).findAll();
    }

    @Test
    void testSaveTask() throws IllegalDateException {
        // Datos de prueba
        Task task = new Task("Test Task", "Description", LocalDateTime.now().plusDays(1));

        // Simular comportamiento del repositorio
        when(repository.save(task)).thenReturn(task);

        // Invocar el método
        Optional<Task> result = taskManagementAdapter.saveTask(task);

        // Verificaciones
        assertTrue(result.isPresent());
        assertEquals(task, result.get());
        verify(repository, times(1)).save(task);
    }

    @Test
    void testUpdateTask() throws IllegalDateException {
        // Datos de prueba
        Task task = new Task("Test Task", "Description", LocalDateTime.now().plusDays(1));

        // Simular comportamiento del repositorio
        when(repository.save(task)).thenReturn(task);

        // Invocar el método
        Optional<Task> result = taskManagementAdapter.updateTask(task);

        // Verificaciones
        assertTrue(result.isPresent());
        assertEquals(task, result.get());
        verify(repository, times(1)).save(task);
    }

    @Test
    void testSaveAll() throws IllegalDateException {
        // Datos de prueba
        Task task1 = new Task("Task 1", "Description 1", LocalDateTime.now().plusDays(1));
        Task task2 = new Task("Task 2", "Description 2", LocalDateTime.now().plusDays(1));
        List<Task> tasks = Arrays.asList(task1, task2);

        // Simular comportamiento del repositorio
        when(repository.saveAll(tasks)).thenReturn(tasks);

        // Invocar el método
        List<Task> result = taskManagementAdapter.saveAll(tasks);

        // Verificaciones
        assertEquals(2, result.size());
        assertTrue(result.contains(task1));
        assertTrue(result.contains(task2));
        verify(repository, times(1)).saveAll(tasks);
    }
}