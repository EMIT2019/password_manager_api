package com.emit.password_manager_api.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.emit.password_manager_api.controller.PlataformController;
import com.emit.password_manager_api.dto.PlataformDto;
import com.emit.password_manager_api.dto.mapper.PlataformMapper;
import com.emit.password_manager_api.service.plataform.PlataformService;
import com.emit.password_manager_api.model.*;

@RequestMapping("/plataform")
@RestController
public class PlataformControllerImpl implements PlataformController {

	private final PlataformService pService;
	private final PlataformMapper mapper;
	
	public PlataformControllerImpl(PlataformService plataformService, PlataformMapper plataformMapper) {
		this.pService = plataformService;
		this.mapper = plataformMapper;
	}
	
	@Override
	public ResponseEntity<List<PlataformDto>> findAll() {
		
		List<PlataformDto> plataformDtoList = pService.findAll().stream()
				.map(mapper::toGetDtoEntity)
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(plataformDtoList);
	}

	@Override
	public ResponseEntity<PlataformDto> findById(Long id) {
		
		Plataform plataform;
		
		plataform = pService.findById(id);
		
		return ResponseEntity.ok(mapper.toGetDtoEntity(plataform));
	}

	@Override
	public ResponseEntity<PlataformDto> save(PlataformDto dtoEntity) {
		
		Plataform plataform, savedPlataform;
		
		plataform = mapper.toPostEntity(dtoEntity);
		savedPlataform = pService.save(plataform);
		
		return new ResponseEntity<PlataformDto>(mapper.toGetDtoEntity(savedPlataform), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<PlataformDto> update(Long id, PlataformDto dtoEntity) {
		
		Plataform plataform, updatedPlataform;
		dtoEntity.setId_plataform(id);
		
		plataform = mapper.toPostEntity(dtoEntity);
		updatedPlataform = pService.update(plataform);
		
		return ResponseEntity.ok(mapper.toGetDtoEntity(updatedPlataform));
	}

	@Override
	public void logicalRemove(Long id, PlataformDto dtoEntity) {
		
		Plataform plataform;
		dtoEntity.setId_plataform(id);
		
		plataform = mapper.toPostEntity(dtoEntity);
		pService.update(plataform);
	}

	@Override
	public void physicalRemove(Long id) {
		// TODO Auto-generated method stub
		
	}

}
