package net.jdazher.tasks.infrastructure.scheduler;

import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.service.DomainTaskService;
import net.jdazher.domain.tasks.service.TaskService;
import net.jdazher.tasks.infrastructure.events.TaskDueEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Component
public class TaskScheduler {


    private final ApplicationEventPublisher applicationEventPublisher;
    private final DomainTaskService taskService;
    public TaskScheduler(TaskService taskService, ApplicationEventPublisher applicationEventPublisher) {
        this.taskService = (DomainTaskService) taskService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void checkIfAnyTaskIsDue() {
        System.out.println("HOLA: " + LocalDateTime.now().toString());
        List<Task> tasks = taskService.getAllTasks();
        List<Task> filteredTasks = tasks.stream().filter(Task::checkIfTaskIsDue).toList();

        filteredTasks.forEach(task -> applicationEventPublisher.publishEvent(new TaskDueEvent(task.getId())));
    }
}
