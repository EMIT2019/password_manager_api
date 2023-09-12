package com.emit.password_manager_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "plataform")
public class Plataform implements ModelEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_plataform")
	private Long id_plataform;
	
	@NotEmpty(message = "The field plataform_name must not be empty")
	@Column(name = "plataform_name")
	private String plataform_name;
	
	@Column(name = "img")
	private String img;
}
