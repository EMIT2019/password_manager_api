package com.emit.password_manager_api.dto;

import lombok.*;

@Data
public class KeywordDto implements DtoEntity {
	private Long id_keyword;
	private OperationDto operationDto;
	private PlataformDto plataformDto;
	private String keyword;
}
