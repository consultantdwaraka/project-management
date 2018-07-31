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

import com.project.management.bootstrap.documents.ProjectDetails;
import com.project.management.bootstrap.documents.UserDetails;
import com.project.management.bootstrap.services.ProjectService;

@RestController
@RequestMapping(value = "/services/v1")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@RequestMapping(value = "/getProjects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<List<ProjectDetails>> getProjects() {
		List<ProjectDetails> projectList = projectService.getProjects();
		return new ResponseEntity<>(projectList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addProject", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<ProjectDetails> getUsersList(@RequestBody ProjectDetails projectDetails) {
		ProjectDetails projectDetailsResponse = projectService.addProject(projectDetails);
		return new ResponseEntity<>(projectDetailsResponse, HttpStatus.OK);

	}

	@RequestMapping(value = "/deleteProject/{projectId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public  ResponseEntity<UserDetails> getUsersList(@PathVariable String projectId) {
		this.projectService.deleteProject(projectId);
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
