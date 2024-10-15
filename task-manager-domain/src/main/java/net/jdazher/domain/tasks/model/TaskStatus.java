package net.jdazher.domain.tasks.model;

/**
 * An enumeration representing the various statuses a task can have in the system.
 *
 * <p>This enum defines the lifecycle of a task from creation to completion or cancellation.</p>
 *
 * <p>Possible statuses include:</p>
 * <ul>
 *   <li>{@link #CREATED} - The task has been created but not yet started.</li>
 *   <li>{@link #IN_PROGRESS} - The task is currently in progress.</li>
 *   <li>{@link #COMPLETED} - The task has been completed successfully.</li>
 *   <li>{@link #CANCELLED} - The task has been cancelled.</li>
 *   <li>{@link #DUE} - The task's due date has passed without completion.</li>
 * </ul>
 */
public enum TaskStatus {
    CREATED, COMPLETED, CANCELLED, IN_PROGRESS, DUE;
}
