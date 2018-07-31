package com.project.management.bootstrap.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.management.bootstrap.documents.TaskDetails;
import com.project.management.bootstrap.documents.UserDetails;
import com.project.management.bootstrap.services.TaskService;

@RestController
@RequestMapping(value = "/services/v1")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@RequestMapping(value = "/getTasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<TaskDetails>> getTasks() {
		final List<TaskDetails> taskList = taskService.getTasks();
		return new ResponseEntity<>(taskList, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/addTask", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<TaskDetails> addTask(@RequestBody TaskDetails taskDetails) {
		TaskDetails taskDetailsResponse = taskService.addTask(taskDetails);
		return new ResponseEntity<>(taskDetailsResponse, HttpStatus.OK);

	}

	@RequestMapping(value = "/deleteTask/{taskId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public  ResponseEntity<UserDetails> deleteTask(@PathVariable String taskId) {
		this.taskService.deleteTask(taskId);
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
