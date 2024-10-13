package net.jdazher.domain.tasks.model;

import lombok.*;


@Getter
@Setter
@ToString

public class TaskTag {

    private String tag;

    private TaskTag(String tag) {
        if (tag.isBlank()) {
            throw new IllegalArgumentException("Tag cannot be empty.");
        }
        this.tag = tag;
    }

    public static TaskTag valueOf(String tag) {
        return new TaskTag(tag);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof TaskTag taskTag)) return false;

        return getTag().equals(taskTag.getTag());
    }

    @Override
    public int hashCode() {
        return getTag().hashCode();
    }
}
