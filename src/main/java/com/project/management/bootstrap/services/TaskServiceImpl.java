package com.project.management.bootstrap.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.management.bootstrap.documents.TaskDetails;
import com.project.management.bootstrap.repo.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public List<TaskDetails> getTasks() {
		return taskRepository.findAll();
	}

	@Override
	public TaskDetails addTask(TaskDetails taskDetails) {
		return taskRepository.save(taskDetails);
	}

	@Override
	public String deleteTask(String taskId) {
		taskRepository.deleteById(taskId);
		return taskId;
	}

}
