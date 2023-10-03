package com.emit.password_manager_api.model;

import java.sql.Date;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "audit_keyword")
public class AuditKeyword implements ModelEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_audit_keyword")
	private Long id_audit_keyword;
	
	@NotNull(message = "The field id_keyword needs to be specified")
	@JoinColumn(name = "id_keyword")
	@ManyToOne
	private Keyword keyword;
	
	@NotNull(message = "The field id_operation needs to be specified")
	@JoinColumn(name = "id_operation")
	@ManyToOne
	private Operation operation;
	
	@NotNull(message = "The field id_plataform ")
	@Column(name = "id_plataform")
	private Long id_plataform;
	
	@NotEmpty(message = "The field current_value must not be empty")
	@Column(name = "keyword")
	private String keyword_value;
	
	@NotNull(message = "The field action_date must not be empty.")
	@Column(name = "action_date")
	private Date action_date;
}
