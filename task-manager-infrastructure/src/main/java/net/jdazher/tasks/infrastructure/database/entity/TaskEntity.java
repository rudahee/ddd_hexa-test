package net.jdazher.tasks.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public final class TaskEntity implements Serializable {


    @Serial
    private static final long serialVersionUID = 324223400129374L;

    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String description;
    private ZonedDateTime dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatusEntity status;

    @ElementCollection
    @CollectionTable(name = "task_tag")
    private List<String> tags;

}
