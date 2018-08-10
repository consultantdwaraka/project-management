package com.project.management.bootstrap.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.management.bootstrap.documents.ProjectDetails;

/**
 * @author Dwaraka Konangi
 *
 */
public interface ProjectRepository extends MongoRepository<ProjectDetails, String> {

}
