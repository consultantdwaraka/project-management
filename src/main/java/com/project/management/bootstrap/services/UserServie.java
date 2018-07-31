/**
 * 
 */
package com.project.management.bootstrap.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.management.bootstrap.documents.UserDetails;
import com.project.management.bootstrap.repo.UserRepository;

/**
 * @author Dwaraka Konangi
 *
 */
@Service
public class UserServie {

	@Autowired
	private UserRepository userRepository;

	public List<UserDetails> getUsers() {
		return this.userRepository.findAll();
	}

	public UserDetails saveUser(UserDetails userDetails) {
		return this.userRepository.save(userDetails);
	}

	public String deleteUser(String userID) {
		this.userRepository.deleteById(userID);
		return userID;
	}

}
