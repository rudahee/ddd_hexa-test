package net.hexaddd.taskmanager.domain.model;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TaskTag {

    private UUID id;
    private String tag;

}
