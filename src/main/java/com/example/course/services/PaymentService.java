package com.example.course.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.course.dto.PaymentDTO;
import com.example.course.entities.Order;
import com.example.course.entities.Payment;
import com.example.course.repositories.OrderRepository;
import com.example.course.repositories.PaymentRepository;
import com.example.course.services.exceptions.DatabaseException;
import com.example.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository repository;

	@Autowired
	private OrderRepository orderRepository;

	public List<PaymentDTO> findAll(){
		List<Payment> list = repository.findAll();
		return list.stream().map(PaymentDTO::new).collect(Collectors.toList());
	}

	public PaymentDTO findById(Long id) {
		Payment entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new PaymentDTO(entity);
	}

	public PaymentDTO insert(PaymentDTO dto) {

		Payment entity = new Payment();

		copyDtoToEntity(dto, entity);

		entity = repository.save(entity);

		return new PaymentDTO(entity);
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

	public PaymentDTO update(Long id, PaymentDTO dto) {
		try {
			Payment entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);

			return new PaymentDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void copyDtoToEntity (PaymentDTO dto, Payment entity) {
		entity.setMoment(dto.getMoment());

		Order order = orderRepository.getReferenceById(dto.getOrderId());

		entity.setOrder(order);
	}
}
