package com.emit.password_manager_api.model;

import java.util.Date;
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
	
	@NotEmpty(message = "The field prev_value must not be empty")
	@Column(name = "prev_value")
	private String prev_value;
	
	@NotEmpty(message = "The field current_value must not be empty")
	@Column(name = "current_value")
	private String current_value;
	
	@NotEmpty(message = "The field action_date must not be empty")
	@Column(name = "action_date")
	private Date action_date;
}
