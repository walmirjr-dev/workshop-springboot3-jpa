package com.example.course.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.course.dto.UserDTO;
import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;
import com.example.course.services.exceptions.InvalidInputException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService service;

	@Test
	void insert_ShouldValidateEmailAndPhone_WhenDataIsValid () {
		UserDTO user = new UserDTO();
		user.setName("test");
		user.setEmail("test@gmail.com");
		user.setPhone("11927123888");
		user.setId(1L);
		user.setPassword("1234");

		when(userRepository.save(any())).thenReturn(new User());

		assertDoesNotThrow(()-> service.insert(user));
	}

	@Test
	void insert_ShouldThrowException_WhenEmailIsInvalid() {
		UserDTO user = new UserDTO();
		user.setName("test");
		user.setEmail("abcde"); //invalid email address
		user.setPhone("11927123888");
		user.setId(1L);
		user.setPassword("1234");

		assertThrows(InvalidInputException.class, () -> service.insert(user));
	}

	@Test
	void insert_ShouldThrowException_WhenPhoneIsInvalid() {
		UserDTO user = new UserDTO();
		user.setName("test");
		user.setEmail("test@gmail.com");
		user.setPhone("119"); //invalid phone number
		user.setId(1L);
		user.setPassword("1234");

		assertThrows(InvalidInputException.class, () -> service.insert(user));
	}

	@Test
	void insert_ShouldThrowException_WhenPhoneAndEmailAreInvalid() {
		UserDTO user = new UserDTO();
		user.setName("test");
		user.setEmail("abcde"); //invalid email address
		user.setPhone("11927"); //invalid phone number
		user.setId(1L);
		user.setPassword("1234");

		assertThrows(InvalidInputException.class, () -> service.insert(user));
	}


}
