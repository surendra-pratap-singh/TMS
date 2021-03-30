package com.tms.task.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tms.task.dto.TaskDto;
import com.tms.task.exception.InvalidRequestException;
import com.tms.task.exception.RecordNotFoundExceptionObject;
import com.tms.task.models.Task;
import com.tms.task.repositories.TaskRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TaskServiceImpl implements TaskService {
	@Autowired
	TaskRepository taskRepository;

	@Override
	public List<Task> getAllTasks() {
		log.info("Getting all tasks!");
		return taskRepository.findAll();
	}

	@Override
	public Task save(TaskDto dto) {
		log.info("Save new Task!");
		return taskRepository.save(Task.builder().taskName(dto.getTaskName()).timeSpentOnTask(dto.getTimeSpentOnTask())
				.taskGroup(dto.getTaskGroup()).assignee(dto.getAssignee()).parentTask(dto.getParentTask() != null ? dto.getParentTask() : 0).build());
	}

	@Override
	public Task update(TaskDto taskDto) {
		log.info("Update task!");
		Optional<Task> optionalTask = taskRepository.findById(taskDto.getId());
		if (optionalTask.isEmpty())
			throw new RecordNotFoundExceptionObject("Record not find by id : " + taskDto.getId());
		Task task = optionalTask.get();
		if (taskDto.isFinished()) {
			List<Task> subTasks = taskRepository.findByParentTask(taskDto.getId());
			if (subTasks != null && subTasks.size() > 0) {
				subTasks.stream().forEach(st -> {
					if (!st.isFinished())
						throw new InvalidRequestException("Subtask required to finished first!");
				});
			}
		}
		task.setFinished(taskDto.isFinished());
		return taskRepository.save(task);
	}

	@Override
	public Task delete(Long id) {
		log.info("Delete task by : "+id);
		Optional<Task> optionalTask = taskRepository.findById(id);
		if (optionalTask.isEmpty())
			throw new RecordNotFoundExceptionObject("Task not find by id : " + id);
		Task task = optionalTask.get();
		List<Task> subTasks = taskRepository.findByParentTask(id);
		if (subTasks != null && subTasks.size() > 0) {
			subTasks.stream().forEach(st -> {
				taskRepository.delete(st);
			});
		}
		taskRepository.delete(task);
		return task;
	}

	@Override
	public Task findById(Long id) {
		Optional<Task> optionalTask = taskRepository.findById(id);
		if (optionalTask.isEmpty())
			throw new RecordNotFoundExceptionObject("Task not find by id : " + id);
		return optionalTask.get();
	}

}
