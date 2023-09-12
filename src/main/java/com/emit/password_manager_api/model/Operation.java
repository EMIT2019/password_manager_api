package com.emit.password_manager_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "operation")
public class Operation implements ModelEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_operation")
	private Long id_operation;
	
	@NotEmpty(message = "The field operation_name must not be empty")
	@Column(name = "operation_name")
	private String operation_name;
}
