package com.emit.password_manager_api.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emit.password_manager_api.controller.KeywordController;
import com.emit.password_manager_api.dto.KeywordDto;
import com.emit.password_manager_api.dto.OperationDto;
import com.emit.password_manager_api.dto.mapper.KeywordMapper;
import com.emit.password_manager_api.model.Keyword;
import com.emit.password_manager_api.service.keyword.KeywordService;

@RequestMapping("/key")
@RestController
public class KeywordControllerImpl implements KeywordController {

	private final KeywordService kService;
	private final KeywordMapper kMapper;
	
	public KeywordControllerImpl(KeywordService keywordService, KeywordMapper keywordMapper) {
		this.kService = keywordService;
		this.kMapper = keywordMapper;
	}
	
	@Override
	public ResponseEntity<List<KeywordDto>> findAll() {
		
		List<KeywordDto> keywordDtoList = kService.findAll().stream()
				.map(kMapper::toGetDtoEntity)
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(keywordDtoList);
	}

	@Override
	public ResponseEntity<KeywordDto> findById(Long id) {
		
		Keyword keyword;
		
		keyword = kService.findById(id);
		
		return ResponseEntity.ok(kMapper.toGetDtoEntity(keyword));
	}

	@Override
	public ResponseEntity<KeywordDto> save(KeywordDto dtoEntity) {
		
		Keyword keyword, savedKeyword;
		OperationDto dtoOperation = new OperationDto();
		
		dtoOperation.setId_operation((long) 1);
		dtoEntity.setOperationDto(dtoOperation);
		
		keyword = kMapper.toPostEntity(dtoEntity);
		savedKeyword = kService.save(keyword);
		kService.auditKeyword(savedKeyword);
		return new ResponseEntity<KeywordDto>(kMapper.toGetDtoEntity(savedKeyword), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<KeywordDto> update(Long id, KeywordDto dtoEntity) {
		
		Keyword keyword, updatedKeyword;
		OperationDto dtoOperation = new OperationDto();
		
		dtoOperation.setId_operation((long) 2);
		dtoEntity.setOperationDto(dtoOperation);
		dtoEntity.setId_keyword(id);
		
		keyword = kMapper.toPostEntity(dtoEntity);
		updatedKeyword = kService.update(keyword);
		kService.auditKeyword(updatedKeyword);
		return ResponseEntity.ok(kMapper.toGetDtoEntity(updatedKeyword));
	}

	@Override
	public void logicalRemove(Long id, KeywordDto dtoEntity) {
		
		Keyword keyword, removedKeyword;
		OperationDto dtoOperation = new OperationDto();
		
		dtoOperation.setId_operation((long) 3);
		dtoEntity.setOperationDto(dtoOperation);
		dtoEntity.setId_keyword(id);
		
		keyword = kMapper.toPostEntity(dtoEntity);
		removedKeyword = kService.update(keyword);
		kService.auditKeyword(removedKeyword);
	}

	@Override
	public void physicalRemove(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResponseEntity<List<KeywordDto>> getPage(Integer page) {
		List<KeywordDto> keywordDtoList = kService.findPage(page).stream()
				.map(kMapper::toGetDtoEntity)
				.collect(Collectors.toList());
		return ResponseEntity.ok(keywordDtoList);
	}

}
