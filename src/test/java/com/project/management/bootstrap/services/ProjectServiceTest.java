package com.project.management.bootstrap.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.project.management.bootstrap.documents.ProjectDetails;
import com.project.management.bootstrap.repo.ProjectRepository;

/**
 * @author Dwaraka Konangi
 *
 */
public class ProjectServiceTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock
	private ProjectRepository projectRepository;

	@InjectMocks
	private ProjectServiceImpl projectServie;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetUsers() {
		when(projectRepository.findAll()).thenReturn(new ArrayList<>());
		assertTrue(projectServie.getProjects().size() == 0);
	}

	@Test
	public void testAddUser() {
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setId("1");

		when(projectRepository.save(projectDetails)).thenReturn(projectDetails);
		assertEquals(projectServie.addProject(projectDetails).getId(), projectDetails.getId());
	}
}
