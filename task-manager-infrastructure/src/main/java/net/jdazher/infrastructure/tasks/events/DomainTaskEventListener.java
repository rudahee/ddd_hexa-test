package net.jdazher.infrastructure.tasks.events;

import lombok.NoArgsConstructor;

import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.model.TaskStatus;
import net.jdazher.infrastructure.tasks.database.adapters.MongoDBTaskManagementAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


/**
 * Listener class for handling task-related events in the domain layer.
 *
 * <p>This class listens for {@link TaskDueEvent} instances and updates the corresponding
 * task's status in the task repository.</p>
 *
 * <p>It interacts with the {@link MongoDBTaskManagementAdapter} to retrieve and update tasks
 * based on the events it listens to.</p>
 */
@Component
@NoArgsConstructor
public class DomainTaskEventListener {

    private MongoDBTaskManagementAdapter taskRepo;

    /**
     * Constructs a new {@code DomainTaskEventListener} with the specified
     * {@link MongoDBTaskManagementAdapter}.
     *
     * @param taskRepo The adapter used for accessing and managing task data.
     */
    @Autowired
    public DomainTaskEventListener(MongoDBTaskManagementAdapter taskRepo) {
        this.taskRepo = taskRepo;
    }

    /**
     * Handles {@link TaskDueEvent} when it is published.
     *
     * <p>This method retrieves the task associated with the event's ID, changes its status
     * to {@link TaskStatus#DUE}, and updates the task in the repository.</p>
     *
     * @param event The {@link TaskDueEvent} containing the ID of the task to update.
     */
    @EventListener(TaskDueEvent.class)
    public void handleTaskDueEvent(TaskDueEvent event) {
        Task task = taskRepo.getTaskById(event.getId()).orElseThrow(() ->
                new IllegalArgumentException("Task not found for ID: " + event.getId()));

        task.changeStatus(TaskStatus.DUE);
        taskRepo.updateTask(task);
    }
}
