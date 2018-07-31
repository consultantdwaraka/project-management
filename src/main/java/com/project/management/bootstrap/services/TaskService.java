package com.project.management.bootstrap.services;

import java.util.List;

import com.project.management.bootstrap.documents.TaskDetails;

public interface TaskService {

	public List<TaskDetails> getTasks();

	public TaskDetails addTask(TaskDetails taskDetails);

	public String deleteTask(String taskId);

}
