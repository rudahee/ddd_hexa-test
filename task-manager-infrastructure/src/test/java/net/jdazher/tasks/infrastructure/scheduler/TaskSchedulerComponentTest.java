package net.jdazher.tasks.infrastructure.scheduler;

import net.jdazher.domain.tasks.error.IllegalDateException;
import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.model.TaskStatus;
import net.jdazher.domain.tasks.service.DomainTaskService;
import net.jdazher.infrastructure.tasks.events.TaskDueEvent;
import net.jdazher.infrastructure.tasks.scheduler.TaskSchedulerComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskSchedulerComponentTest {

    @Mock
    private DomainTaskService taskService;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private TaskSchedulerComponent taskSchedulerComponent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializar los mocks
    }

    @Test
    void testCheckIfAnyTaskIsDueWithDueTasks() throws Exception {
        // Crear un Task real, y configurarlo como vencido
        try {
            Task dueTask = null;

            dueTask = new Task("Test Task", "This task is due", LocalDateTime.now().minusDays(1));
            dueTask.changeStatus(TaskStatus.IN_PROGRESS); // Para que esté en progreso y pueda vencerse

            Task notDueTask = new Task("Test Task 2", "This task is not due", LocalDateTime.now().plusDays(1));
            notDueTask.changeStatus(TaskStatus.IN_PROGRESS);

            List<Task> tasks = List.of(dueTask, notDueTask);

            // Simular que el servicio devuelve estas tareas
            when(taskService.getAllTasks()).thenReturn(tasks);

            // Ejecutar el método que estamos probando
            taskSchedulerComponent.checkIfAnyTaskIsDue();

            // Capturar el evento publicado
            ArgumentCaptor<TaskDueEvent> eventCaptor = ArgumentCaptor.forClass(TaskDueEvent.class);
            verify(eventPublisher, times(1)).publishEvent(eventCaptor.capture());

            // Verificar que se publicó un evento para la tarea vencida
            TaskDueEvent event = eventCaptor.getValue();
            assertEquals(dueTask.getId(), event.getId());
        } catch (Exception e) {
            assertInstanceOf(IllegalDateException.class, e);
        }
    }

    @Test
    void testCheckIfAnyTaskIsDueWithNoDueTasks() throws Exception {
        // Crear instancias reales de Task sin estar vencidas
        Task notDueTask1 = new Task("Task 1", "Not due task", LocalDateTime.now().plusDays(1));
        notDueTask1.changeStatus(TaskStatus.IN_PROGRESS);

        Task notDueTask2 = new Task("Task 2", "Another not due task", LocalDateTime.now().plusDays(2));
        notDueTask2.changeStatus(TaskStatus.IN_PROGRESS);

        List<Task> tasks = List.of(notDueTask1, notDueTask2);

        // Simular que el servicio devuelve estas tareas
        when(taskService.getAllTasks()).thenReturn(tasks);

        // Ejecutar el método que estamos probando
        taskSchedulerComponent.checkIfAnyTaskIsDue();

        // Verificar que no se publicó ningún evento
        verify(eventPublisher, never()).publishEvent(any(TaskDueEvent.class));
    }

    @Test
    void testCheckIfAnyTaskIsDueWithEmptyTaskList() {
        // Simular que el servicio devuelve una lista vacía
        when(taskService.getAllTasks()).thenReturn(new ArrayList<>());

        // Ejecutar el método que estamos probando
        taskSchedulerComponent.checkIfAnyTaskIsDue();

        // Verificar que no se publicó ningún evento
        verify(eventPublisher, never()).publishEvent(any(TaskDueEvent.class));
    }
}