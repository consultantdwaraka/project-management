package com.project.management.bootstrap.api;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.project.management.bootstrap.documents.TaskDetails;
import com.project.management.bootstrap.services.TaskService;
import com.project.management.bootstrap.services.TaskServiceImpl;

@RunWith(SpringRunner.class)
public class TaskControllerTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	private MockMvc mockMvc;
	
	@Mock
	private TaskServiceImpl taskService;

	@InjectMocks
	private TaskController taskController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(taskController).addFilters(new CorsFilter()).build();
	}

	@Test
	public void testAddTasks() throws Exception {
		List<TaskDetails> taskDetails = new ArrayList<>();
		when(taskService.getTasks()).thenReturn(taskDetails);
		this.mockMvc.perform(get("/services/v1/getTasks")).andExpect(status().isOk());
		verify(taskService, times(1)).getTasks();
	}
	
	@Test
	public void testAddProject() throws Exception {
		TaskDetails taskDetails = new TaskDetails();
		taskDetails.setId("1");
		taskDetails.setEndDate("");
		taskDetails.setStartDate("");
		taskDetails.setParentTask("");
		taskDetails.setTaskName("");
		taskDetails.setPriority("");
		taskDetails.setUserName("");

		Gson gson = new Gson();

		when(taskService.addTask(taskDetails)).thenReturn(taskDetails);
		this.mockMvc
				.perform(put("/services/v1/addTask").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(gson.toJson(taskDetails)))
				.andExpect(status().isOk()).andReturn();

	}

	@Test
	public void testAddProject_withNoContent() throws Exception {
		TaskDetails taskDetails = new TaskDetails();
		taskDetails.setId("1");
		taskDetails.setEndDate("");
		taskDetails.setStartDate("");
		taskDetails.setParentTask("");
		taskDetails.setTaskName("");
		taskDetails.setPriority("");
		taskDetails.setUserName("");

		when(taskService.addTask(taskDetails)).thenReturn(taskDetails);
		this.mockMvc.perform(put("/services/v1/addTask").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();

	}
	
	@Test
	public void testDeleteTask() throws Exception {

		when(taskService.deleteTask("1")).thenReturn("1");
		this.mockMvc.perform(delete("/services/v1/deleteTask/1").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

	}

}
