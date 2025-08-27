package com.example.course.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.course.dto.ProductDTO;
import com.example.course.dto.ProductResponseDTO;
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

	public List<ProductResponseDTO> findAll() {
        List<Product> list = repository.findAll();
        return list.stream().map(ProductResponseDTO::new).collect(Collectors.toList());
    }

    public ProductResponseDTO findById(Long id) {
        Product entity = repository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(id));
        return new ProductResponseDTO(entity);
    }

	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();

		copyDtoToEntity(dto, entity);

		entity = repository.save(entity);

		return new ProductDTO(entity);
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

	public ProductDTO update(Long id, ProductDTO dto) {

		try {
	        Product entity = repository.getReferenceById(id);
	        copyDtoToEntity(dto, entity);
	        entity = repository.save(entity);

	        return new ProductDTO(entity);
	    } catch (EntityNotFoundException e) {
	        throw new ResourceNotFoundException(id);
	    }
	}

	private void copyDtoToEntity (ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
	    entity.setDescription(dto.getDescription());
	    entity.setPrice(dto.getPrice());
	    entity.setImgUrl(dto.getImgUrl());

		entity.getCategories().clear();

		for (Long catID : dto.getCategoryIDs()) {
			Category category = catReposiroy.getReferenceById(catID);
			entity.getCategories().add(category);
		}
	}

}
