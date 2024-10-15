package net.jdazher.domain.tasks.model;

import lombok.*;

/**
 * A class representing a tag that can be associated with a task. Each tag is a simple string
 * that provides additional categorization or context for the task.
 *
 * <p>This class provides methods for creating and validating task tags, as well as overrides
 * for {@code equals} and {@code hashCode} to ensure proper comparison and usage in collections.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@link Getter} - Generates getter methods for all fields.</li>
 *   <li>{@link Setter} - Generates setter methods for all fields.</li>
 *   <li>{@link NoArgsConstructor} - Generates a no-argument constructor for the class.</li>
 *   <li>{@link ToString} - Generates a {@code toString} method for the class.</li>
 * </ul>
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TaskTag {

    private String tag;

    /**
     * Private constructor that initializes a TaskTag with a given tag string.
     *
     * @param tag The tag string. Must not be empty or blank.
     * @throws IllegalArgumentException if the tag is blank.
     */
    private TaskTag(String tag) {
        if (tag.isBlank()) {
            throw new IllegalArgumentException("Tag cannot be empty.");
        }
        this.tag = tag;
    }

    /**
     * Static factory method for creating a TaskTag instance from a string.
     *
     * @param tag The string representing the tag.
     * @return A new instance of {@code TaskTag} initialized with the provided string.
     */
    public static TaskTag valueOf(String tag) {
        return new TaskTag(tag);
    }

    /**
     * Checks if this {@code TaskTag} is equal to another object.
     *
     * @param other The object to compare against.
     * @return {@code true} if the other object is a {@code TaskTag} and has the same tag string, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof TaskTag taskTag)) return false;

        return getTag().equals(taskTag.getTag());
    }

    /**
     * Returns a hash code value for the {@code TaskTag}.
     *
     * @return The hash code of the tag string.
     */
    @Override
    public int hashCode() {
        return getTag().hashCode();
    }
}
