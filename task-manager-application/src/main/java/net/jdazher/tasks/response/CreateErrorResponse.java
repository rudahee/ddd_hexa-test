package net.jdazher.tasks.rest.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateErrorResponse implements Response {

    private String message;

    @JsonCreator
    public CreateErrorResponse(@JsonProperty("message") final String message) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("message cannot be null");
        }
        this.message = message;
    }
}
