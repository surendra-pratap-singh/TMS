package com.tms.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

	private Long id;

	private String taskName;

	private String timeSpentOnTask;

	private String taskGroup;

	private String assignee;

	private Long parentTask;

	private boolean finished = false;
}
