package com.emit.password_manager_api.dto;

import lombok.*;

@Data
public class OperationDto implements DtoEntity {
	private Long id_operation;
	private String operation_name;
}
