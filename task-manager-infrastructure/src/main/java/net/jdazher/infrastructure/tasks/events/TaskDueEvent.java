package net.jdazher.infrastructure.tasks.events;

import net.jdazher.domain.tasks.event.TaskEvent;
import org.springframework.context.ApplicationEvent;

/**
 * Event class representing a task that is due.
 *
 * <p>This class extends {@link ApplicationEvent} and implements {@link TaskEvent},
 * encapsulating information about a task that has reached its due date.</p>
 *
 * <p>The event carries the ID of the task that is due, allowing other components of
 * the application to react appropriately, such as updating the task's status.</p>
 */
public class TaskDueEvent extends ApplicationEvent implements TaskEvent {

    private String id;

    /**
     * Constructs a new {@code TaskDueEvent} with the specified source.
     *
     * <p>If the source is an instance of {@code String}, it is treated as the
     * task ID and assigned to the {@code id} field.</p>
     *
     * @param source The object that triggered the event, typically the task ID as a string.
     */
    public TaskDueEvent(Object source) {
        super(source);

        if (source instanceof String taskId) {
            this.id = taskId;
        }
    }

    /**
     * Returns the ID of the task associated with this event.
     *
     * @return The ID of the task that is due.
     */
    @Override
    public String getId() {
        return id;
    }
}