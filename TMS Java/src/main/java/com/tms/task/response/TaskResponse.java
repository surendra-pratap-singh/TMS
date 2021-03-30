package com.tms.task.response;

import java.util.List;

import com.tms.task.models.Task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

	private int id;

	private String taskName;

	private String timeSpentOnTask;

	private String taskGroup;

	private String assignee;

	private List<Task> subtasks;

	private boolean finished = false;

}
