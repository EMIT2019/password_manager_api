package com.emit.password_manager_api.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "keyword")
public class Keyword implements ModelEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_keyword")
	private Long id_keyword;
	
	@NotNull(message = "The field id_operation needs to be specified.")
	@JoinColumn(name = "id_operation")
	@ManyToOne
	private Operation operation;
	
	@NotEmpty(message = "The field keyword must not be empty")
	@Column(name = "keyword")
	private String keyword;
}
