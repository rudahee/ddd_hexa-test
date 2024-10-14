package net.jdazher.tasks.rest.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.jdazher.domain.tasks.model.Task;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class CreateTaskRequest implements Serializable {

    private Task task;
    @JsonCreator
    public CreateTaskRequest(@JsonProperty("task") final Task task) {
        if (task == null) {
            throw new IllegalArgumentException("task cannot be null");
        }
        this.task = task;
    }
}
