package com.emit.password_manager_api.dto.mapper.impl;

import com.emit.password_manager_api.dto.AuditKeywordDto;
import com.emit.password_manager_api.dto.mapper.*;
import com.emit.password_manager_api.model.AuditKeyword;
import org.springframework.stereotype.Component;

@Component
public class AuditKeywordMapperImpl implements AuditKeywordMapper {

	private final KeywordMapper keywordMapper;
	private final OperationMapper operationMapper;
	
	public AuditKeywordMapperImpl(KeywordMapper keywordMapper, OperationMapper operationMapper) {
		this.keywordMapper = keywordMapper;
		this.operationMapper = operationMapper;
	}
	
	@Override
	public AuditKeywordDto toGetDtoEntity(AuditKeyword entity) {
		AuditKeywordDto auditKeywordDto = new AuditKeywordDto();
		auditKeywordDto.setId_audit_keyword(entity.getId_audit_keyword());
		auditKeywordDto.setKeyword(
				keywordMapper.toGetDtoEntity(entity.getKeyword())
				);
		auditKeywordDto.setOperation(
				operationMapper.toGetDtoEntity(entity.getOperation())
				);
		auditKeywordDto.setPrev_value(entity.getPrev_value());
		auditKeywordDto.setCurrent_value(entity.getCurrent_value());
		auditKeywordDto.setAction_date(entity.getAction_date());
		
		return auditKeywordDto;
	}

	@Override
	public AuditKeyword toPostEntity(AuditKeywordDto dto) {
		AuditKeyword auditKeyword = new AuditKeyword();
		auditKeyword.setId_audit_keyword(dto.getId_audit_keyword());
		auditKeyword.setKeyword(
				keywordMapper.toPostEntity(dto.getKeyword())
				);
		auditKeyword.setOperation(
				operationMapper.toPostEntity(dto.getOperation())
				);
		auditKeyword.setPrev_value(dto.getPrev_value());
		auditKeyword.setCurrent_value(dto.getCurrent_value());
		auditKeyword.setAction_date(dto.getAction_date());
		
		return auditKeyword;
	}

}
