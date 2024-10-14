package net.jdazher.tasks.rest.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.jdazher.domain.tasks.model.Task;
@NoArgsConstructor
@Data
public class CreateTaskResponse implements Response {

    private Task task;

    @JsonCreator
    public CreateTaskResponse(@JsonProperty("task") final Task task) {
        if (task == null) {
            throw new IllegalArgumentException("task cannot be null");
        }
        this.task = task;
    }
}
