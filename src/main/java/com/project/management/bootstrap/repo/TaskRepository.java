package com.project.management.bootstrap.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.management.bootstrap.documents.TaskDetails;

public interface TaskRepository extends MongoRepository<TaskDetails, String> {

}
