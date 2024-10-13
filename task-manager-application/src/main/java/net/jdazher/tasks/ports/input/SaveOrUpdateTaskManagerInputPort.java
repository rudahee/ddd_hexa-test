package net.jdazher.tasks.ports.input;

import net.jdazher.tasks.use_cases.SaveAllTasksUseCase;
import net.jdazher.tasks.use_cases.SaveTaskUseCase;
import net.jdazher.tasks.use_cases.UpdateTaskUseCase;

public interface SaveOrUpdateTaskManagerInputPort extends SaveTaskUseCase, SaveAllTasksUseCase, UpdateTaskUseCase {


}
