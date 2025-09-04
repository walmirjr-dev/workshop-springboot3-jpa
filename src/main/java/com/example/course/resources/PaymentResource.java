package com.example.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.course.dto.PaymentDTO;
import com.example.course.services.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/payments")
@Tag(name = "Payments", description = "Manage payments")
public class PaymentResource {

	@Autowired
	private PaymentService service;


	@GetMapping
	@Operation(summary = "Lists all payments")
	public ResponseEntity<List<PaymentDTO>> findAll(){
		List<PaymentDTO> list = service.findAll();

		return ResponseEntity.ok().body(list);

	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "Get payment by its ID")
	@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Payment found"),
        @ApiResponse(responseCode = "404", description = "Payment not found")
    })
	public ResponseEntity<PaymentDTO> findById(@PathVariable Long id){
		PaymentDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	@Operation(summary = "Create new payment")
	public ResponseEntity<PaymentDTO> insert(@RequestBody PaymentDTO dto){
		PaymentDTO newDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete a payment by its ID")
	@ApiResponses({
        @ApiResponse(responseCode = "204", description = "Payment deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Payment not found"),
        @ApiResponse(responseCode = "409", description = "Cannot delete payment: has associate tables")
    })
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	@Operation(summary = "Update a payment by its ID")
	@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Payment found"),
        @ApiResponse(responseCode = "404", description = "Payment not found")
    })
	public ResponseEntity<PaymentDTO>update(@PathVariable Long id, @RequestBody PaymentDTO dto ){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

}
