package net.jdazher.application.tasks.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.jdazher.domain.tasks.model.Task;

import java.io.Serializable;

/**
 * A request class used for creating a new Task. This class contains a Task object
 * and provides constructors for initializing it.
 *
 * <p>This class is serializable and uses the {@code @JsonCreator} annotation to
 * facilitate deserialization from JSON. If the task is null during construction,
 * it throws an {@link IllegalArgumentException}.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@link NoArgsConstructor} - Generates a no-argument constructor for the class.</li>
 *   <li>{@link Data} - Generates getter, setter, and other utility methods (e.g., toString, equals, hashCode).</li>
 *   <li>{@link JsonCreator} - Indicates how the constructor should be used during deserialization from JSON.</li>
 *   <li>{@link JsonProperty} - Maps the "task" property in the JSON to the {@code task} field.</li>
 * </ul>
 *
 * <p>This class implements {@link Serializable} to allow it to be serialized and deserialized.</p>
 */
@NoArgsConstructor
@Data
public class CreateTaskRequest implements Serializable {

    private Task task;


    /**
     * Constructor that initializes the CreateTaskRequest with a Task.
     *
     * @param task The Task to be created. Cannot be null.
     * @throws IllegalArgumentException if the task is null.
     */
    @JsonCreator
    public CreateTaskRequest(@JsonProperty("task") final Task task) {
        if (task == null) {
            throw new IllegalArgumentException("task cannot be null");
        }
        this.task = task;
    }
}
