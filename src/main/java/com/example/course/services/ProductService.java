package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.course.entities.Category;
import com.example.course.entities.Product;
import com.example.course.repositories.CategoryRepository;
import com.example.course.repositories.ProductRepository;
import com.example.course.services.exceptions.DatabaseException;
import com.example.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private CategoryRepository catReposiroy;

	public List<Product> findAll(){
		return repository.findAll();
	}

	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}

	public Product insert(Product obj) {
		return repository.save(obj);
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

	public Product update(Long id, Product obj) {

		try {
			Product entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Product entity, Product obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setPrice(obj.getPrice());
		entity.setImgUrl(obj.getImgUrl());

		entity.getCategories().clear();

		 for (Category categoryFromRequest : obj.getCategories()) {
			 Category categoryFromDb = catReposiroy.findById(categoryFromRequest.getId())
				 .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

		        entity.getCategories().add(categoryFromDb);
		    }
	}

}
