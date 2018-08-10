package com.project.management.bootstrap.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.management.bootstrap.documents.ProjectDetails;
import com.project.management.bootstrap.repo.ProjectRepository;

/**
 * @author Dwaraka Konangi
 *
 */
@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public List<ProjectDetails> getProjects() {
		return this.projectRepository.findAll();
	}

	@Override
	public ProjectDetails addProject(ProjectDetails projectDetails) {
		return this.projectRepository.save(projectDetails);
	}

	@Override
	public String deleteProject(String projectId) {
		this.projectRepository.deleteById(projectId);
		return projectId;
	}

}
