package com.tms.task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tms.task.dto.TaskDto;
import com.tms.task.response.Response;
import com.tms.task.service.TaskService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/task")
public class TaskController {

	@Autowired
	TaskService taskService;

	@PostMapping(value = "/")
	public Response save(@RequestBody TaskDto taskDto) {
		return new Response(HttpStatus.OK.value(), taskService.save(taskDto));
	}
	
	@GetMapping(value = "/task-list")
	public Response findAllTasks() {
		return new Response(HttpStatus.OK.value(), taskService.getAllTasks());
	}
	
	@GetMapping(value = "/task/{id}")
	public Response findById(@PathVariable Long id) {
		return new Response(HttpStatus.OK.value(), taskService.findById(id));
	}

	@PutMapping(value = "/update-task")
	public Response update(@RequestBody TaskDto taskDto) {
		return new Response(HttpStatus.OK.value(), taskService.update(taskDto));
	}

	@DeleteMapping(value = "/delete-task/{id}")
	public Response delete(@PathVariable Long id) {
		return new Response(HttpStatus.OK.value(), taskService.delete(id));
	}
	
}
