package com.emit.password_manager_api.dto;

import lombok.*;

@Data
public class PlataformDto implements DtoEntity {
	private Long id_plataform;
	private String plataform_name;
	private String img;
}
