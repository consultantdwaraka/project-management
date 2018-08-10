package com.project.management.bootstrap.api;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.project.management.bootstrap.documents.UserDetails;
import com.project.management.bootstrap.services.UserServie;

/**
 * @author Dwaraka Konangi
 *
 */
@RunWith(SpringRunner.class)
public class UserControllerTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	private MockMvc mockMvc;

	@Mock
	private UserServie userServie;

	@InjectMocks
	private UserController userController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).addFilters(new CorsFilter()).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUsers() throws Exception {
		List<UserDetails> userDetailsList = new ArrayList<>();
		UserDetails userDetails = new UserDetails();
		userDetails.setEmployeeId("1");
		userDetails.setFirstName("First Name");
		userDetails.setLastName("Last Name");
		userDetails.setId("1");
		userDetailsList.add(userDetails);
		when(userServie.getUsers()).thenReturn(userDetailsList);
		this.mockMvc.perform(get("/services/v1/getUsers")).andExpect(status().isOk());
		verify(userServie, times(1)).getUsers();

	}

	@Test
	public void testGetUsers_with_invalidUrl() throws Exception {
		this.mockMvc.perform(get("/service/v1/getUsers")).andExpect(status().isNotFound());

	}

	@Test
	public void testAddUser() throws Exception {
		UserDetails userDetails = new UserDetails();
		userDetails.setEmployeeId("1");
		userDetails.setFirstName("First Name");
		userDetails.setLastName("Last Name");
		userDetails.setId("1");

		Gson gson = new Gson();

		when(userServie.saveUser(userDetails)).thenReturn(userDetails);
		this.mockMvc
				.perform(put("/services/v1/addUser").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(gson.toJson(userDetails)))
				.andExpect(status().isOk()).andReturn();

	}

	@Test
	public void testAddUser_withNoContent() throws Exception {
		UserDetails userDetails = new UserDetails();
		userDetails.setEmployeeId("1");
		userDetails.setFirstName("First Name");
		userDetails.setLastName("Last Name");
		userDetails.setId("1");

		when(userServie.saveUser(userDetails)).thenReturn(userDetails);
		this.mockMvc.perform(
				put("/services/v1/addUser").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andReturn();

	}
	
	@Test
	public void testDeleteUser() throws Exception {

		when(userServie.deleteUser("1")).thenReturn("1");
		this.mockMvc.perform(delete("/services/v1/deleteUser/1").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

	}

}
