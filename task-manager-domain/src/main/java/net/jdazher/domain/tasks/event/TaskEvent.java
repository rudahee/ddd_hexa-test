package net.jdazher.domain.tasks.event;


/**
 * An interface representing a task-related event in the system.
 *
 * <p>This interface defines the basic contract for any event associated with tasks,
 * requiring implementations to provide an identifier for the event.</p>
 */
public interface TaskEvent {

    /**
     * Retrieves the unique identifier of the task event.
     *
     * @return A {@link String} representing the unique ID of the event.
     */
    String getId();

}
