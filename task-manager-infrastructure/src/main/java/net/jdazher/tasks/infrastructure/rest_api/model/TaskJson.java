package net.jdazher.tasks.infrastructure.rest_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import net.jdazher.domain.tasks.model.TaskTag;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public final class TaskJson {
    private UUID id;
    private String title;
    private String description;
    private ZonedDateTime dueDate;
    private TaskStatusJson status;
    private List<TaskTagJson> tags;
}
