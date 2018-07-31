package com.project.management.bootstrap.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.management.bootstrap.documents.UserDetails;
import com.project.management.bootstrap.services.UserServie;

@RestController()
@RequestMapping("/services/v1")
public class UserController {

	@Autowired
	private UserServie userService;

	@RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<UserDetails>> getUsersList() {
		List<UserDetails> userDetailsList = userService.getUsers();
		return new ResponseEntity<>(userDetailsList, HttpStatus.OK);

	}

	@RequestMapping(value = "/addUser", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResponseEntity<UserDetails> getUsersList(@RequestBody UserDetails userDetails) {
		UserDetails userDetailsResponse = userService.saveUser(userDetails);
		return new ResponseEntity<>(userDetailsResponse, HttpStatus.OK);

	}

	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.DELETE, produces = "application/json")
	public  ResponseEntity<UserDetails> getUsersList(@PathVariable String userId) {
		this.userService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
