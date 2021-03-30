package com.tms.task.service;

import java.util.List;

import com.tms.task.dto.TaskDto;
import com.tms.task.models.Task;

public interface TaskService {

	List<Task> getAllTasks();
	
	Task save(TaskDto taskDto);
	
	Task update(TaskDto taskDto);

	Task delete(Long id);

	Task findById(Long id);
	
}
