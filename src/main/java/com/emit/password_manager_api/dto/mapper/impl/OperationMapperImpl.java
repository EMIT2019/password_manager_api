package com.emit.password_manager_api.dto.mapper.impl;

import com.emit.password_manager_api.dto.OperationDto;
import com.emit.password_manager_api.dto.mapper.*;
import com.emit.password_manager_api.model.Operation;
import org.springframework.stereotype.Component;


@Component
public class OperationMapperImpl implements OperationMapper {
	
	@Override
	public OperationDto toGetDtoEntity(Operation entity) {
		OperationDto operationDto = new OperationDto();
		operationDto.setId_operation(entity.getId_operation());
		operationDto.setOperation_name(entity.getOperation_name());
		return operationDto;
	}

	@Override
	public Operation toPostEntity(OperationDto dto) {
		Operation operation = new Operation();
		operation.setId_operation(dto.getId_operation());
		operation.setOperation_name(dto.getOperation_name());
		return operation;
	}

}
