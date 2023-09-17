package com.emit.password_manager_api.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.emit.password_manager_api.controller.OperationController;
import com.emit.password_manager_api.dto.OperationDto;
import com.emit.password_manager_api.dto.mapper.OperationMapper;
import com.emit.password_manager_api.model.Operation;
import com.emit.password_manager_api.service.operation.OperationService;

@RequestMapping("/operation")
@RestController
public class OperationControllerImpl implements OperationController {

	private final OperationService oService;
	private final OperationMapper mapper;
	
	public OperationControllerImpl(OperationService operationService, OperationMapper operationMapper) {
		this.oService = operationService;
		this.mapper = operationMapper;
	}
	
	@Override
	public ResponseEntity<List<OperationDto>> findAll() {
		
		List<OperationDto> operationDtoList = oService.findAll().stream()
				.map(mapper::toGetDtoEntity)
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(operationDtoList);
	}

	@Override
	public ResponseEntity<OperationDto> findById(Long id) {
		
		Operation operation;
		
		operation = oService.findById(id);
		
		return ResponseEntity.ok(mapper.toGetDtoEntity(operation));
	}

	@Override
	public ResponseEntity<OperationDto> save(OperationDto dtoEntity) {
		
		Operation operation, savedOperation;
		
		operation = mapper.toPostEntity(dtoEntity);
		savedOperation = oService.save(operation);
		
		return new ResponseEntity<OperationDto>(mapper.toGetDtoEntity(savedOperation), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<OperationDto> update(Long id, OperationDto dtoEntity) {
		
		Operation operation, updatedOperation;
		dtoEntity.setId_operation(id);
		
		operation = mapper.toPostEntity(dtoEntity);
		updatedOperation = oService.update(operation);
		
		return ResponseEntity.ok(mapper.toGetDtoEntity(updatedOperation));
	}

	@Override
	public void logicalRemove(Long id, OperationDto dtoEntity) {
		
		Operation operation;
		dtoEntity.setId_operation(id);
		
		operation = mapper.toPostEntity(dtoEntity);
		oService.update(operation);
		
	}

	@Override
	public void physicalRemove(Long id) {
		oService.remove(id);
	}

}
