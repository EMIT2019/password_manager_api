package com.emit.password_manager_api.dto;

import lombok.*;

@Data
public class PlataformKeywordDto implements DtoEntity {
	private Long id_plataform_keyword;
	private PlataformDto plataform;
	private KeywordDto keyword;
}
