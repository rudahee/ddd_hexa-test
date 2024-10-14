package net.jdazher.domain.tasks.event;


import net.jdazher.domain.tasks.model.Task;

public class TaskDueEvent {

    private String id;

    public TaskDueEvent(Task task) {
        this.id = task.getId();
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskDueEvent that)) return false;

        return this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "TaskDueEvent{" +
                "id='" + id + '\'' +
                '}';
    }
}
