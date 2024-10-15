package net.jdazher.infrastructure.tasks.scheduler;

import lombok.extern.log4j.Log4j2;
import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.service.DomainTaskService;
import net.jdazher.domain.tasks.service.TaskService;
import net.jdazher.infrastructure.tasks.events.TaskDueEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Component that schedules the checking of tasks to determine if any are due.
 *
 * <p>This class is responsible for periodically scanning the task repository to
 * identify tasks that are due and publish events for those tasks.</p>
 */
@Component
@Log4j2
public class TaskSchedulerComponent {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final DomainTaskService taskService;

    /**
     * Constructs a new {@code TaskSchedulerComponent} with the specified
     * {@link DomainTaskService} and {@link ApplicationEventPublisher}.
     *
     * @param taskService The service used for task management.
     * @param applicationEventPublisher The event publisher to notify task due events.
     */
    public TaskSchedulerComponent(TaskService taskService, ApplicationEventPublisher applicationEventPublisher) {
        this.taskService = (DomainTaskService) taskService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * Scheduled method that checks for tasks that are due.
     *
     * <p>This method runs every minute and publishes a {@link TaskDueEvent} for
     * each task that is found to be due.</p>
     */
    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void checkIfAnyTaskIsDue() {
        log.info("Daily checking if any tasks are due");
        List<Task> tasks = taskService.getAllTasks();
        List<Task> filteredTasks = tasks.stream().filter(Task::checkIfTaskIsDue).toList();

        filteredTasks.forEach(task -> applicationEventPublisher.publishEvent(new TaskDueEvent(task.getId())));
        log.info("Found " + filteredTasks.size() + " tasks due");
    }
}

