package com.project.management.bootstrap.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.management.bootstrap.documents.TaskDetails;
import com.project.management.bootstrap.repo.TaskRepository;
import com.project.management.bootstrap.utils.StatusEnum;

/**
 * @author Dwaraka Konangi
 *
 */
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
		taskDetails.setStatus(StatusEnum.IN_PROGRESS.getStatus());
		return taskRepository.save(taskDetails);
	}

	@Override
	public String deleteTask(String taskId) {
		taskRepository.deleteById(taskId);
		return taskId;
	}

	@Override
	public TaskDetails endTask(String taskId) {
		Optional<TaskDetails> optionalTaskDetails = taskRepository.findById(taskId);
		if (optionalTaskDetails.isPresent()) {
			TaskDetails taskDetails = optionalTaskDetails.get();
			taskDetails.setStatus(StatusEnum.COMPLETED.getStatus());
			return taskRepository.save(taskDetails);
		}
		return null;
	}

}
