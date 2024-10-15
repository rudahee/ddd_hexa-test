package net.jdazher.domain.tasks.model;

import net.jdazher.domain.tasks.error.IllegalDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private List<TaskTag> sampleTags;

    @BeforeEach
    void setUp() {
        sampleTags = Arrays.asList(TaskTag.valueOf("urgent"), TaskTag.valueOf("work"));
    }

    @Test
    void testTaskConstructorWithValidDueDate() throws IllegalDateException {
        LocalDateTime futureDate = LocalDateTime.now().plusDays(5);
        Task task = new Task("Title", "Description", futureDate, sampleTags);

        assertNotNull(task.getId());
        assertEquals("Title", task.getTitle());
        assertEquals("Description", task.getDescription());
        assertEquals(futureDate, task.getDueDate());
        assertEquals(TaskStatus.CREATED, task.getStatus());
        assertEquals(sampleTags, task.getTags());
    }

    @Test
    void testTaskConstructorWithInvalidDueDate_pastDate() {
        LocalDateTime pastDate = LocalDateTime.now().minusDays(1);
        IllegalDateException exception = assertThrows(IllegalDateException.class, () ->
                new Task("Title", "Description", pastDate, sampleTags));

        assertEquals("Due date cannot be in the past.", exception.getMessage());
    }

    @Test
    void testTaskConstructorWithInvalidDueDate_futureDateBeyondLimit() {
        LocalDateTime futureDateBeyondLimit = LocalDateTime.now().plusYears(2);
        IllegalDateException exception = assertThrows(IllegalDateException.class, () ->
                new Task("Title", "Description", futureDateBeyondLimit, sampleTags));

        assertEquals("Due date must be in reasonable time.", exception.getMessage());
    }

    @Test
    void testChangeStatusValidChange() throws IllegalDateException {
        LocalDateTime futureDate = LocalDateTime.now().plusDays(5);
        Task task = new Task("Title", "Description", futureDate, sampleTags);

        task.changeStatus(TaskStatus.IN_PROGRESS);
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }

    @Test
    void testChangeStatusInvalidChangeToCompletedOrDue() throws IllegalDateException {
        LocalDateTime futureDate = LocalDateTime.now().plusDays(5);
        Task task = new Task("Title", "Description", futureDate, sampleTags);

        task.changeStatus(TaskStatus.COMPLETED);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                task.changeStatus(TaskStatus.IN_PROGRESS));

        assertEquals("Cannot change status of completed or due tasks.", exception.getMessage());
    }

    @Test
    void testChangeStatusInvalidChangeToCreated() throws IllegalDateException {
        LocalDateTime futureDate = LocalDateTime.now().plusDays(5);
        Task task = new Task("Title", "Description", futureDate, sampleTags);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                task.changeStatus(TaskStatus.CREATED));

        assertEquals("Cannot change status to initial state.", exception.getMessage());
    }


    @Test
    void testCheckIfTaskIsDueNotDueTask() throws IllegalDateException {
        LocalDateTime futureDate = LocalDateTime.now().plusDays(1);
        Task task = new Task("Title", "Description", futureDate, sampleTags);

        assertFalse(task.checkIfTaskIsDue());
    }

    @Test
    void testTaskConstructorWithTags() throws IllegalDateException {
        LocalDateTime futureDate = LocalDateTime.now().plusDays(3);
        Task task = new Task("Title", "Description", futureDate, sampleTags);

        assertNotNull(task.getTags());
        assertEquals(2, task.getTags().size());
        assertTrue(task.getTags().contains(TaskTag.valueOf("urgent")));
        assertTrue(task.getTags().contains(TaskTag.valueOf("work")));
    }

    @Test
    void testTaskTagValueOfWithValidTag() {
        TaskTag tag = TaskTag.valueOf("homework");

        assertNotNull(tag);
        assertEquals("homework", tag.getTag());
    }

    @Test
    void testTaskTagValueOfWithBlankTag() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                TaskTag.valueOf(""));

        assertEquals("Tag cannot be empty.", exception.getMessage());
    }
}
