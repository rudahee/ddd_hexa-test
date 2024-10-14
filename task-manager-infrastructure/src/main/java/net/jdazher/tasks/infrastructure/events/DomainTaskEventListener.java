package net.jdazher.tasks.events;

import lombok.NoArgsConstructor;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class DomainTaskEventListener implements ApplicationListener<TaskDueEvent> {
    @EventListener(TaskDueEvent.class)
    public void handleTaskDueEvent(TaskDueEvent event) {
        System.out.println("Received spring custom event - " + event.getId());
    }

    @Override
    public void onApplicationEvent(TaskDueEvent event) {
        System.out.println("APP_EVENT: Received spring custom event - " + event.getId());
    }
}
