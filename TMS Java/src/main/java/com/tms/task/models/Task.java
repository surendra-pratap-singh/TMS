package com.tms.task.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TASK")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String taskName;
	
	private String timeSpentOnTask;
	
	private String taskGroup;
	
	private String assignee;
	
	private Long parentTask;
	
	private boolean finished;
	
}
