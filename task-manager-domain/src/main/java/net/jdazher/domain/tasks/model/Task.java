package net.jdazher.domain.tasks.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.jdazher.domain.tasks.error.IllegalDateException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 * A class that represents a task in the system. Each task has a title, description, due date, status,
 * and a list of associated tags. The task can be created, updated, and tracked for its status and due date.
 *
 * <p>This class is immutable once created, except for the status, which can change during the task lifecycle.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@link Getter} - Generates getter methods for all fields.</li>
 *   <li>{@link ToString} - Generates a {@code toString} method.</li>
 *   <li>{@link EqualsAndHashCode} - Generates {@code equals} and {@code hashCode} methods.</li>
 *   <li>{@link NoArgsConstructor} - Generates a no-argument constructor for the class.</li>
 * </ul>
 *
 * <p>This class implements {@link Serializable} to allow it to be serialized.</p>
 */

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public final class Task implements Serializable {

    private String id;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private TaskStatus status;
    private List<TaskTag> tags;

    /**
     * Constructor to create a Task with a title, description, due date, and tags.
     * The task is assigned a unique ID, and its status is set to {@code TaskStatus.CREATED}.
     *
     * @param title       The title of the task.
     * @param description The description of the task.
     * @param dueDate     The due date of the task. Cannot be in the past or too far in the future.
     * @param tags        A list of {@link TaskTag} associated with the task.
     * @throws IllegalDateException if the due date is in the past or more than a year in the future.
     */
    public Task(String title, String description, LocalDateTime dueDate, List<TaskTag> tags) throws IllegalDateException {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        if (checkInitialDueDate(dueDate))
            this.dueDate = dueDate;
        this.status = TaskStatus.CREATED;
        this.tags = tags;
    }

    /**
     * Constructor to create a Task with a title, description, and due date without tags.
     * The task is assigned a unique ID, and its status is set to {@code TaskStatus.CREATED}.
     *
     * @param title       The title of the task.
     * @param description The description of the task.
     * @param dueDate     The due date of the task. Cannot be in the past or too far in the future.
     * @throws IllegalDateException if the due date is in the past or more than a year in the future.
     */
    public Task(String title, String description, LocalDateTime dueDate) throws IllegalDateException {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        if (checkInitialDueDate(dueDate))
            this.dueDate = dueDate;
        this.status = TaskStatus.CREATED;
        this.tags = new ArrayList<>();
    }

    /**
     * Constructor to create a Task with an existing ID, title, description, due date, status, and tags.
     *
     * @param id          The unique identifier of the task.
     * @param title       The title of the task.
     * @param description The description of the task.
     * @param dueDate     The due date of the task. Cannot be in the past or too far in the future.
     * @param status      The current status of the task.
     * @param tags        A list of {@link TaskTag} associated with the task.
     * @throws IllegalDateException if the due date is in the past or more than a year in the future.
     */
    public Task(UUID id, String title, String description, LocalDateTime dueDate, TaskStatus status, List<TaskTag> tags) throws IllegalDateException {
        this.id = id.toString();
        this.title = title;
        this.description = description;
        this.status = status;
        if (checkInitialDueDate(dueDate))
            this.dueDate = dueDate;
        this.tags = tags;
    }

    /**
     * Changes the status of the task to the provided {@code status}.
     *
     * @param status The new {@link TaskStatus} to set.
     * @throws IllegalStateException if the task is already in {@code DUE}, {@code COMPLETED}, or {@code CANCELLED} status,
     *                               or if the new status is {@code CREATED}.
     */
    public void changeStatus(TaskStatus status) {
        if (this.status == TaskStatus.DUE
                || this.status == TaskStatus.COMPLETED
                || this.status == TaskStatus.CANCELLED) {
            throw new IllegalStateException("Cannot change status of completed or due tasks.");
        } else if (status == TaskStatus.CREATED) {
            throw new IllegalStateException("Cannot change status to initial state.");
        } else {
            this.status = status;
        }
    }

    /**
     * Checks if the due date is valid. The due date must not be in the past or more than a year in the future.
     *
     * @param dueDate The due date to validate.
     * @return {@code true} if the due date is valid.
     * @throws IllegalDateException if the due date is invalid (in the past or too far in the future).
     */
    public boolean checkInitialDueDate(LocalDateTime dueDate) throws IllegalDateException {
        if (dueDate.isBefore(LocalDateTime.now())) {
            throw new IllegalDateException("Due date cannot be in the past.");
        }

        if (dueDate.isAfter(LocalDateTime.now().plusYears(1))) {
            throw new IllegalDateException("Due date must be in reasonable time.");
        }

        return true;
    }

    /**
     * Checks if the task is due based on its status and due date.
     *
     * @return {@code true} if the task is due, otherwise {@code false}.
     */
    public boolean checkIfTaskIsDue() {
        if (this.status == TaskStatus.IN_PROGRESS || this.status == TaskStatus.CREATED) {
            return this.dueDate.isBefore(LocalDateTime.now());
        }
        return false;
    }
}