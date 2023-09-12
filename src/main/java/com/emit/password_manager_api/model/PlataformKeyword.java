package com.emit.password_manager_api.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "plataform_keyword")
public class PlataformKeyword implements ModelEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_plataform_keyword")
	private Long id_plataform_keyword;

	@NotNull(message = "The field id_keyword needs to be specified")
	@JoinColumn(name = "id_keyword")
	@ManyToOne
	private Keyword keyword;
	
	@NotNull(message = "The field id_plataform needs to be specified")
	@JoinColumn(name = "id_plataform")
	@ManyToOne
	private Plataform plataform;
}
