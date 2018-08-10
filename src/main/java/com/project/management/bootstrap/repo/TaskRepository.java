package com.project.management.bootstrap.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.management.bootstrap.documents.TaskDetails;

/**
 * @author Dwaraka Konangi
 *
 */
public interface TaskRepository extends MongoRepository<TaskDetails, String> {

}
