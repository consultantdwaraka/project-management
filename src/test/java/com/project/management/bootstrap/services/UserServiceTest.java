package com.project.management.bootstrap.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.management.bootstrap.documents.UserDetails;
import com.project.management.bootstrap.repo.UserRepository;

@RunWith(SpringRunner.class)
public class UserServiceTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServie userServie;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUsers() {
		when(userRepository.findAll()).thenReturn(new ArrayList<>());
		assertTrue(userServie.getUsers().size() == 0);
	}

	@Test
	public void testAddUser() {
		UserDetails userDetails = new UserDetails();
		userDetails.setEmployeeId("1");
		userDetails.setFirstName("First Name");
		userDetails.setLastName("Last Name");
		userDetails.setId("1");

		when(userRepository.save(userDetails)).thenReturn(userDetails);
		assertEquals(userServie.saveUser(userDetails).getId(), userDetails.getId());
	}

}
