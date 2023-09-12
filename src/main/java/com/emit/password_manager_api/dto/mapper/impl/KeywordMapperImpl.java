package com.emit.password_manager_api.dto.mapper.impl;

import com.emit.password_manager_api.dto.KeywordDto;
import com.emit.password_manager_api.dto.mapper.*;
import com.emit.password_manager_api.model.Keyword;
import org.springframework.stereotype.Component;

@Component
public class KeywordMapperImpl implements KeywordMapper {

	private final OperationMapper operationMapper;
	
	public KeywordMapperImpl(OperationMapper operationMapper) {
		this.operationMapper = operationMapper;
	}
	
	@Override
	public KeywordDto toGetDtoEntity(Keyword entity) {
		KeywordDto keywordDto = new KeywordDto();
		keywordDto.setId_keyword(entity.getId_keyword());
		keywordDto.setOperationDto(
				operationMapper.toGetDtoEntity(entity.getOperation())
				);
		keywordDto.setKeyword(entity.getKeyword());
		return null;
	}

	@Override
	public Keyword toPostEntity(KeywordDto dto) {
		Keyword keyword = new Keyword();
		keyword.setId_keyword(dto.getId_keyword());
		keyword.setOperation(
				operationMapper.toPostEntity(dto.getOperationDto())
				);
		keyword.setKeyword(dto.getKeyword());
		return keyword;
	}

}
