package com.example.course.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.course.dto.UserDTO;
import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;
import com.example.course.services.exceptions.DatabaseException;
import com.example.course.services.exceptions.InvalidInputException;
import com.example.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<UserDTO> findAll(){
		List<User> list = repository.findAll();
		return list.stream().map(UserDTO::new).collect(Collectors.toList());
	}

	public UserDTO findById(Long id) {
		User entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new UserDTO(entity);
	}

	public UserDTO insert(UserDTO dto) {

		validateUserData(dto);

		User entity = new User();

		copyDtoToEntity(dto, entity);

		entity = repository.save(entity);

		return new UserDTO(entity);
	}

	public void delete(Long id) {

		if (!repository.existsById(id)) {
	        throw new ResourceNotFoundException(id);
	    }

		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public UserDTO update(Long id, UserDTO dto) {

		validateUserData(dto);

		try {
			User entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);

			return new UserDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void copyDtoToEntity (UserDTO dto, User entity) {
		entity.setName(dto.getName());
	    entity.setEmail(dto.getEmail());
	    entity.setPhone(dto.getPhone());
	    entity.setPassword(dto.getPassword());
	}

	private void validateUserData(UserDTO dto) {
		if(!dto.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
			throw new InvalidInputException("Invalid email format: " + dto.getEmail());
		}

		if(!dto.getPhone().matches("^\\d{10,11}$")) {
			throw new InvalidInputException("Invalid phone number format: " + dto.getPhone());
		}
	}
}
