package com.emit.password_manager_api.repository.specification.Parameters;

public enum AuditKeywordParameters {
	AUDIT_ID_FIELD,
	AUDIT_KEYWORD_FIELD,
	AUDIT_OPERATION_FIELD,
	AUDIT_ID_PLATAFORM_FIELD,
	AUDIT_KEYWORD_VALUE_FIELD,
	AUDIT_DATE_FIELD;
	
	public String getValue() {
		switch(this) {
		case AUDIT_ID_FIELD:
			return "id_audit_keyword";
		case AUDIT_KEYWORD_FIELD:
			return "keyword";
		case AUDIT_OPERATION_FIELD:
			return "operation";
		case
		AUDIT_ID_PLATAFORM_FIELD:
			return "id_plataform";
		case AUDIT_KEYWORD_VALUE_FIELD:
			return "keyword_value";
		case AUDIT_DATE_FIELD:
			return "action_date";
		default:
			throw new RuntimeException("The required field does not exists in "+this.getClass()+"");
		}
	}
}
