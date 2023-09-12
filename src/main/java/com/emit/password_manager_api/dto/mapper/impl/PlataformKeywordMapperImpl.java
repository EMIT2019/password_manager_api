package com.emit.password_manager_api.dto.mapper.impl;

import com.emit.password_manager_api.dto.PlataformKeywordDto;
import com.emit.password_manager_api.dto.mapper.*;
import com.emit.password_manager_api.model.PlataformKeyword;
import org.springframework.stereotype.Component;

@Component
public class PlataformKeywordMapperImpl implements PlataformKeywordMapper {

	private final PlataformMapper plataformMapper;
	private final KeywordMapper keywordMapper;
	
	public PlataformKeywordMapperImpl(PlataformMapper plataformMapper, KeywordMapper keywordMapper) {
		this.plataformMapper = plataformMapper;
		this.keywordMapper = keywordMapper;
	}
	
	@Override
	public PlataformKeywordDto toGetDtoEntity(PlataformKeyword entity) {
		PlataformKeywordDto plataformKeywordDto = new PlataformKeywordDto();
		plataformKeywordDto.setId_plataform_keyword(entity.getId_plataform_keyword());
		plataformKeywordDto.setPlataform(
				plataformMapper.toGetDtoEntity(entity.getPlataform())
				);
		plataformKeywordDto.setKeyword(
				keywordMapper.toGetDtoEntity(entity.getKeyword())
				);
		return plataformKeywordDto;
	}

	@Override
	public PlataformKeyword toPostEntity(PlataformKeywordDto dto) {
		PlataformKeyword plataformKeyword = new PlataformKeyword();
		plataformKeyword.setId_plataform_keyword(dto.getId_plataform_keyword());
		plataformKeyword.setPlataform(
				plataformMapper.toPostEntity(dto.getPlataform())
				);
		plataformKeyword.setKeyword(
				keywordMapper.toPostEntity(dto.getKeyword())
				);
		return plataformKeyword;
	}

}
