package com.tms.task.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.task.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByParentTask(Long parentTask);
}
