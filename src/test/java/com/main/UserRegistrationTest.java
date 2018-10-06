package com.main;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.skillEnza.app.entity.User;
import com.skillEnza.app.service.UserRegistrationService;

@RunWith(SpringRunner.class)
// @WebMvcTest(value = UserRegistrationController.class, secure = false)
public class UserRegistrationTest {

	@MockBean
	private UserRegistrationService userService;

	@Before(value = "")
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveUser() throws Exception {
		User user = new User();
		user.setDepartment("departmentName");
		user.setDate("1992/10/16");
		user.setFirstName("rohan");
		user.setLastName("gupta");
		user.setId(5);
		user.setGender("male");
		Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(user);

		User newUser = new User();
		assertThat(userService.createUser(newUser), is(notNullValue()));
		assertThat(userService.createUser(newUser).getFirstName(), is("rohan"));

	}

	@Test(expected = RuntimeException.class)
	public void testAddCustomer_throwsException() {
		Mockito.when(userService.createUser(Mockito.any(User.class))).thenThrow(RuntimeException.class);
		User newUser = new User();
		userService.createUser(newUser);
	}
}
