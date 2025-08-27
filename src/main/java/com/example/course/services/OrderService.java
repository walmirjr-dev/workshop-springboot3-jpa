package com.example.course.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.course.dto.OrderDTO;
import com.example.course.dto.OrderItemDTO;
import com.example.course.dto.OrderResponseDTO;
import com.example.course.entities.Order;
import com.example.course.entities.OrderItem;
import com.example.course.entities.Product;
import com.example.course.entities.User;
import com.example.course.repositories.OrderRepository;
import com.example.course.repositories.ProductRepository;
import com.example.course.repositories.UserRepository;
import com.example.course.services.exceptions.DatabaseException;
import com.example.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	public List<OrderResponseDTO> findAll(){
		List<Order> list = repository.findAll();
		return list.stream().map(OrderResponseDTO::new).collect(Collectors.toList());
	}

	public OrderResponseDTO findById(Long id) {
		Order entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new OrderResponseDTO(entity);
	}

	public OrderDTO insert(OrderDTO dto) {
		Order entity = new Order();
		copyDtoToEntity(dto, entity);

		entity = repository.save(entity);

		return new OrderDTO(entity);
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

	public OrderDTO update(Long id, OrderDTO dto) {

		try {
			Order entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);

			return new OrderDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void copyDtoToEntity (OrderDTO dto, Order entity) {
		entity.setMoment(dto.getMoment());
	    entity.setOrderStatus(dto.getOrderStatus());

	    User client = userRepository.getReferenceById(dto.getClientId());
	    entity.setClient(client);

		entity.getItems().clear();

		for (OrderItemDTO itemDto : dto.getItems()) {
			Product product = productRepository.getReferenceById(itemDto.getProductId());

	        OrderItem item = new OrderItem(entity, product, itemDto.getQuantity(), product.getPrice());
	        entity.getItems().add(item);
		}
	}


}
