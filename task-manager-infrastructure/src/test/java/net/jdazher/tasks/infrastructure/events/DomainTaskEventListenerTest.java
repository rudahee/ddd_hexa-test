package net.jdazher.tasks.infrastructure.events;

import net.jdazher.domain.tasks.error.IllegalDateException;
import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.model.TaskStatus;
import net.jdazher.infrastructure.tasks.database.adapters.MongoDBTaskManagementAdapter;
import net.jdazher.infrastructure.tasks.events.DomainTaskEventListener;
import net.jdazher.infrastructure.tasks.events.TaskDueEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DomainTaskEventListenerTest {


    @Mock
    private MongoDBTaskManagementAdapter taskRepo;

    @InjectMocks
    private DomainTaskEventListener taskEventListener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializar mocks
    }

    @Test
    void testHandleTaskDueEvent() throws IllegalDateException {
        // Datos de prueba
        String taskId = "123";
        Task task = new Task("Test Task", "Task Description", LocalDateTime.now().plusDays(1));

        // Simular la obtención de la tarea
        when(taskRepo.getTaskById(taskId)).thenReturn(Optional.of(task));

        // Crear el evento
        TaskDueEvent event = new TaskDueEvent(taskId);

        // Invocar el listener
        taskEventListener.handleTaskDueEvent(event);

        // Verificar que se cambió el estado de la tarea
        assertEquals(TaskStatus.DUE, task.getStatus());

        // Verificar que se llamó a updateTask en el repositorio
        verify(taskRepo, times(1)).updateTask(task);
    }

}