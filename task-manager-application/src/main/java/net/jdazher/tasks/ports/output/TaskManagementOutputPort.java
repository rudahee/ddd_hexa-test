package net.jdazher.tasks.ports.output;

import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.model.TaskTag;
import net.jdazher.domain.tasks.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskManagementOutputPort extends TaskRepository {

    Optional<Task> getTaskById(UUID id);
    Optional<Task> getTaskByTitle(String title);
    List<Task> getAllTasks();
    Optional<Task> saveTask(Task task);
    Optional<Task> updateTask(Task task);
    List<Task> saveAll(List<Task> tasks);

    public TaskTag transformStringToTag(String tag);

    public default List<TaskTag> transformStringsToTags(List<String> tags) {

        List<TaskTag> taskTags = new ArrayList<>();
        for (String tag : tags) {
            taskTags.add(transformStringToTag(tag));
        }
        return taskTags;
    }

    public String transformTagToString(TaskTag tag);

    public default List<String> transformTagsToStrings(List<TaskTag> tags) {

        List<String> strList = new ArrayList<>();
        for (TaskTag tag : tags) {
            strList.add(transformTagToString(tag));
        }
        return strList;
    }
}
