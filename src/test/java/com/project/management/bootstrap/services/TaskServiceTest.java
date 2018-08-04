package com.project.management.bootstrap.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.management.bootstrap.documents.TaskDetails;
import com.project.management.bootstrap.repo.TaskRepository;
import com.project.management.bootstrap.utils.StatusEnum;

@RunWith(SpringRunner.class)
public class TaskServiceTest {

	@Mock
	private TaskRepository taskRepository;

	@InjectMocks
	private TaskServiceImpl taskService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetUsers() {
		when(taskRepository.findAll()).thenReturn(new ArrayList<>());
		assertTrue(taskService.getTasks().size() == 0);

		TaskDetails taskDetails = new TaskDetails();
		taskDetails.setId("1");
		taskDetails.setEndDate("");
		taskDetails.setStartDate("");
		taskDetails.setTaskName("");
		taskDetails.setParentTask("");
		taskDetails.setPriority("");
		taskDetails.setUserName("");

		List<TaskDetails> taskDetailList = new ArrayList<>();
		taskDetailList.add(taskDetails);

		when(taskRepository.findAll()).thenReturn(taskDetailList);
		assertTrue(taskService.getTasks().size() == 1);
	}

	@Test
	public void testAddTask() {
		TaskDetails taskDetails = new TaskDetails();
		taskDetails.setId("1");
		taskDetails.setEndDate("");
		taskDetails.setStartDate("");
		taskDetails.setTaskName("");
		taskDetails.setParentTask("");
		taskDetails.setPriority("");
		taskDetails.setUserName("");

		when(taskRepository.save(taskDetails)).thenReturn(taskDetails);
		assertEquals(taskService.addTask(taskDetails).getId(), taskDetails.getId());
	}

	@Test
	public void testEndTask() {
		TaskDetails taskDetails = new TaskDetails();
		taskDetails.setId("1");
		taskDetails.setEndDate("");
		taskDetails.setStartDate("");
		taskDetails.setTaskName("");
		taskDetails.setParentTask("");
		taskDetails.setPriority("");
		taskDetails.setUserName("");
		taskDetails.setStatus(StatusEnum.COMPLETED.getStatus());

		when(taskRepository.findById(taskDetails.getId())).thenReturn(Optional.of(taskDetails));
		when(taskRepository.save(taskDetails)).thenReturn(taskDetails);
		assertEquals(taskService.endTask(taskDetails.getId()).getStatus(), taskDetails.getStatus());
	}

}
