package com.project.management.bootstrap.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.management.bootstrap.documents.UserDetails;

/**
 * @author Dwaraka Konangi
 *
 */
public interface UserRepository extends MongoRepository<UserDetails, String>  {

}
