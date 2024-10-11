package net.hexaddd.taskmanager.domain.model;

import jakarta.annotation.Nullable;
import lombok.*;
import net.hexaddd.taskmanager.domain.IllegalDateException;

import java.time.Year;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@ToString
@EqualsAndHashCode
public class Task {


    private UUID id;
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



    public void isInProgress() {
        this.status = TaskStatus.IN_PROGRESS;
    }

    public void isCompleted() {
        this.status = TaskStatus.COMPLETED;
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
        if (this.status == TaskStatus.CREATED || this.status == TaskStatus.IN_PROGRESS
                && this.dueDate.isBefore(ZonedDateTime.now())) {
            this.isDue();
        }
    }


}
