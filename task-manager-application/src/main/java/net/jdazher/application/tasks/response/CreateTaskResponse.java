package net.jdazher.application.tasks.response;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.jdazher.domain.tasks.model.Task;

/**
 * A response class that represents the creation of a Task. This class contains
 * the Task object and is used to send a response back after the Task is created.
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@link NoArgsConstructor} - Generates a no-argument constructor for the class.</li>
 *   <li>{@link Data} - Generates getter, setter, and other utility methods (e.g., toString, equals, hashCode).</li>
 *   <li>{@link JsonCreator} - Specifies how the constructor is used during deserialization from JSON.</li>
 *   <li>{@link JsonProperty} - Maps the "task" property in the JSON to the {@code task} field.</li>
 * </ul>
 *
 * <p>This class implements the {@link Response} interface.</p>
 */
@NoArgsConstructor
@Data
public class CreateTaskResponse implements Response {

    private Task task;

    /**
     * Constructor that initializes the CreateTaskResponse with a Task.
     *
     * @param task The Task that was created. Cannot be null.
     * @throws IllegalArgumentException if the task is null.
     */
    @JsonCreator
    public CreateTaskResponse(@JsonProperty("task") final Task task) {
        if (task == null) {
            throw new IllegalArgumentException("task cannot be null");
        }
        this.task = task;
    }
}
