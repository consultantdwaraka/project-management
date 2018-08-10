package com.project.management.bootstrap.api;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.project.management.bootstrap.documents.ProjectDetails;
import com.project.management.bootstrap.services.ProjectService;

/**
 * @author Dwaraka Konangi
 *
 */
@RunWith(SpringRunner.class)
public class ProjectControllerTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	private MockMvc mockMvc;

	@Mock
	private ProjectService projectService;

	@InjectMocks
	private ProjectController projectController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(projectController).addFilters(new CorsFilter()).build();
	}

	@Test
	public void testGetProjects() throws Exception {
		List<ProjectDetails> projectDetailsList = new ArrayList<>();
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setId("1");
		projectDetails.setCompletedTasks("");
		projectDetails.setEndDate("");
		projectDetails.setStartDate("");
		projectDetails.setPriority("");
		projectDetails.setTasks("");
		projectDetailsList.add(projectDetails);
		when(projectService.getProjects()).thenReturn(projectDetailsList);
		this.mockMvc.perform(get("/services/v1/getProjects")).andExpect(status().isOk());
		verify(projectService, times(1)).getProjects();
	}

	@Test
	public void testGetProjects_with_invalidUrl() throws Exception {
		this.mockMvc.perform(get("/service/v1/getProjects")).andExpect(status().isNotFound());

	}

	@Test
	public void testAddProject() throws Exception {
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setId("1");
		projectDetails.setCompletedTasks("10");
		projectDetails.setEndDate("2010-01-02");
		projectDetails.setStartDate("2010-01-01");
		projectDetails.setPriority("10");
		projectDetails.setTasks("10");
		projectDetails.setProjectName("Junit");

		Gson gson = new Gson();

		when(projectService.addProject(projectDetails)).thenReturn(projectDetails);
		this.mockMvc
				.perform(put("/services/v1/addProject").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(gson.toJson(projectDetails)))
				.andExpect(status().isOk()).andReturn();

	}

	@Test
	public void testAddProject_withNoContent() throws Exception {
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setId("1");

		when(projectService.addProject(projectDetails)).thenReturn(projectDetails);
		this.mockMvc.perform(put("/services/v1/addProject").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();

	}
	
	@Test
	public void testDeleteProject() throws Exception {

		when(projectService.deleteProject("1")).thenReturn("1");
		this.mockMvc.perform(delete("/services/v1/deleteProject/1").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

	}

}
