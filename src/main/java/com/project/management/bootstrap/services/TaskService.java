package com.project.management.bootstrap.services;

import java.util.List;

import com.project.management.bootstrap.documents.TaskDetails;

/**
 * @author Dwaraka Konangi
 *
 */
public interface TaskService {

	public List<TaskDetails> getTasks();

	public TaskDetails addTask(TaskDetails taskDetails);

	public String deleteTask(String taskId);
	
	public TaskDetails endTask(String taskId);

}
