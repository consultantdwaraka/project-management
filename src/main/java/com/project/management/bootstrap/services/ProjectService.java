package com.project.management.bootstrap.services;

import java.util.List;

import com.project.management.bootstrap.documents.ProjectDetails;

public interface ProjectService {

	public List<ProjectDetails> getProjects();
	
	public ProjectDetails addProject(ProjectDetails projectDetails);
	
	public String deleteProject(String projectId);
}
