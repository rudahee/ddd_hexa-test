package net.jdazher.application.tasks.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A response class that represents an error response with a message.
 * This class is used to encapsulate an error message in a structured way.
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@link NoArgsConstructor} - Generates a no-argument constructor for the class.</li>
 *   <li>{@link Data} - Generates getter, setter, and other utility methods (e.g., toString, equals, hashCode).</li>
 *   <li>{@link JsonCreator} - Indicates how the constructor should be used during deserialization from JSON.</li>
 *   <li>{@link JsonProperty} - Maps the "message" property in the JSON to the {@code message} field.</li>
 * </ul>
 *
 * <p>This class implements the {@link Response} interface.</p>
 */
@NoArgsConstructor
@Data
public class CreateErrorResponse implements Response {

    private String message;

    /**
     * Constructor that initializes the CreateErrorResponse with a message.
     *
     * @param message The error message. Cannot be null or blank.
     * @throws IllegalArgumentException if the message is null or blank.
     */
    @JsonCreator
    public CreateErrorResponse(@JsonProperty("message") final String message) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("message cannot be null");
        }
        this.message = message;
    }
}
