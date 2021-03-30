package com.tms.task.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tms.task.dto.TaskDto;
import com.tms.task.service.TaskService;
import com.tms.task.service.TaskServiceImpl;

import io.swagger.annotations.ApiParam;
import jdk.jfr.ContentType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/api/data")
public class DataController {

	@Autowired
	TaskService taskService;

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, headers = ( "content-type=multipart/*"))
	public String sendEmail(@RequestParam("file") MultipartFile file) throws IOException {
		log.info("Uploading data file.");
		System.out.println(file.getBytes());
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
		String line = "";
		String splitBy = ",";
		while ((line = reader.readLine()) != null) {
			String[] data = line.split(splitBy);
			taskService.save(
					TaskDto.builder().taskName(data[0]).timeSpentOnTask(data[1]).taskGroup(data[2]).assignee(data[3])
							.parentTask(Long.parseLong(data[4])).finished(Boolean.parseBoolean(data[5])).build());

		}
		return "File data added successfully!";
	}
}
