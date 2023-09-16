package com.emit.password_manager_api.dto;

import java.sql.Date;
import lombok.*;

@Data
public class AuditKeywordDto implements DtoEntity {
	private Long id_audit_keyword;
	private KeywordDto keyword;
	private OperationDto operation;
	private Long id_plataform;
	private String keyword_value;
	private Date action_date;
}
