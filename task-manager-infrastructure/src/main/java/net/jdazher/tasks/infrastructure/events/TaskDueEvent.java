package net.jdazher.tasks.events;

import net.jdazher.domain.tasks.event.TaskEvent;
import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.model.TaskStatus;
import net.jdazher.domain.tasks.repository.TaskRepository;
import net.jdazher.domain.tasks.service.DomainTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;


@Component
public class TaskDueEvent extends ApplicationEvent implements TaskEvent {

    private String id;

    @Autowired
    private TaskRepository taskService;

    public TaskDueEvent(Object source, String taskId) {
        super(source);
        this.id = taskId;

        Task task = taskService.getTaskById(taskId).get();

        task.changeStatus(TaskStatus.DUE);
        taskService.updateTask(task);
    }

    @Override
    public String getId() {
        return id;
    }
}
