package com.emit.password_manager_api.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emit.password_manager_api.controller.AuditKeywordController;
import com.emit.password_manager_api.dto.AuditKeywordDto;
import com.emit.password_manager_api.dto.mapper.AuditKeywordMapper;
import com.emit.password_manager_api.service.auditKeyword.AuditKeywordService;

@RequestMapping("/audit")
@RestController
public class AuditKeywordControllerImpl implements AuditKeywordController {

	private final AuditKeywordMapper mapper;
	private final AuditKeywordService akService;
	
	public AuditKeywordControllerImpl(AuditKeywordMapper auditKeywordMapper, AuditKeywordService akService) {
		this.mapper = auditKeywordMapper;
		this.akService = akService;
	}
	
	@Override
	public ResponseEntity<List<AuditKeywordDto>> findAll() {
		List<AuditKeywordDto> auditKeywordDtoList = akService.findAll().stream()
				.map(mapper::toGetDtoEntity)
				.collect(Collectors.toList());
		return ResponseEntity.ok(auditKeywordDtoList);
	}

	@Override
	public ResponseEntity<AuditKeywordDto> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<AuditKeywordDto> save(AuditKeywordDto dtoEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<AuditKeywordDto> update(Long id, AuditKeywordDto dtoEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logicalRemove(Long id, AuditKeywordDto dtoEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void physicalRemove(Long id) {
		// TODO Auto-generated method stub
		
	}

}
