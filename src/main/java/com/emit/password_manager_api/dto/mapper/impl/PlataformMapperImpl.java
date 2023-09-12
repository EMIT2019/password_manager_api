package com.emit.password_manager_api.dto.mapper.impl;

import com.emit.password_manager_api.dto.PlataformDto;
import com.emit.password_manager_api.dto.mapper.*;
import com.emit.password_manager_api.model.Plataform;
import org.springframework.stereotype.Component;


@Component
public class PlataformMapperImpl implements PlataformMapper {

	@Override
	public PlataformDto toGetDtoEntity(Plataform entity) {
		PlataformDto plataformDto = new PlataformDto();
		plataformDto.setId_plataform(entity.getId_plataform());
		plataformDto.setPlataform_name(entity.getPlataform_name());
		plataformDto.setImg(entity.getImg());
		return plataformDto;
	}

	@Override
	public Plataform toPostEntity(PlataformDto dto) {
		Plataform plataform = new Plataform();
		plataform.setId_plataform(dto.getId_plataform());
		plataform.setPlataform_name(dto.getPlataform_name());
		plataform.setImg(dto.getImg());
		return plataform;
	}

}
