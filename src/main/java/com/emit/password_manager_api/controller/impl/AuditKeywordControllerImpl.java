package com.emit.password_manager_api.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emit.password_manager_api.controller.AuditKeywordController;
import com.emit.password_manager_api.dto.AuditKeywordDto;
import com.emit.password_manager_api.dto.mapper.AuditKeywordMapper;
import com.emit.password_manager_api.model.AuditKeyword;
import com.emit.password_manager_api.model.Keyword;
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
		AuditKeyword auditKeyword = new AuditKeyword();
		
		auditKeyword = akService.findById(id);
		
		return ResponseEntity.ok(mapper.toGetDtoEntity(auditKeyword));
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

	@Override
	public ResponseEntity<List<AuditKeywordDto>> findByKeyword(Long id, Integer page) {
		Keyword keyword = new Keyword();
		
		keyword.setId_keyword(id);
		
		List<AuditKeywordDto> auditKeywordDtoList = akService.findByKeyword(page, keyword).stream()
				.map(mapper::toGetDtoEntity)
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(auditKeywordDtoList);
	}

	@Override
	public ResponseEntity<List<AuditKeywordDto>> getPage(Integer page) {
		List<AuditKeywordDto> auditKeywordDtoList = akService.findPage(page).stream()
				.map(mapper::toGetDtoEntity)
				.collect(Collectors.toList());
		return ResponseEntity.ok(auditKeywordDtoList);
	}
}
