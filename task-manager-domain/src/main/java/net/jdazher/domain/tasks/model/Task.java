package net.jdazher.domain.tasks.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import net.jdazher.domain.tasks.error.IllegalDateException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@ToString
@EqualsAndHashCode
public final class Task {


    private final UUID id;
    private final String title;
    private final String description;
    private ZonedDateTime dueDate;
    private TaskStatus status;
    private final List<TaskTag> tags;


    public Task(String title, String description, ZonedDateTime dueDate, List<TaskTag> tags) throws IllegalDateException {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        if (checkDate())
            this.dueDate = dueDate;
        this.status = TaskStatus.CREATED;
        this.tags = tags;
    }

    public Task(String title, String description, ZonedDateTime dueDate) throws IllegalDateException {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        if (checkDate())
            this.dueDate = dueDate;
        this.status = TaskStatus.CREATED;
        tags = new ArrayList<>();
    }

    private void setStatus(TaskStatus status) {
        if (this.status==TaskStatus.DUE
                || this.status==TaskStatus.COMPLETED
                || this.status == TaskStatus.CANCELLED) {
            throw new IllegalStateException("Cannot change status of completed or due tasks.");
        } else if (status == TaskStatus.CREATED) {
            throw new IllegalStateException("Cannot change status to initial state.");
        } else {
            this.status = status;
        }
    }

    private void isDue() {
        this.status = TaskStatus.DUE;
    }

    public boolean checkDate() throws IllegalDateException {
        if (this.dueDate.isBefore(ZonedDateTime.now())) {
            throw new IllegalDateException("Due date cannot be in the past.");
        }

        if (this.dueDate.isAfter(ZonedDateTime.now().plusYears(1))) {
            throw new IllegalDateException("Due date must be in reasonable time.");
        }

        return true;
    }

    public void checkStatus() {
        if (this.status == TaskStatus.CREATED && this.dueDate.isBefore(ZonedDateTime.now())) {
            this.isDue();
        }
    }


}
