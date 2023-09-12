package com.emit.password_manager_api.dto;

import java.util.Date;
import lombok.*;

@Data
public class AuditKeywordDto implements DtoEntity {
	private Long id_audit_keyword;
	private KeywordDto keyword;
	private OperationDto operation;
	private String prev_value;
	private String current_value;
	private Date action_date;
}
