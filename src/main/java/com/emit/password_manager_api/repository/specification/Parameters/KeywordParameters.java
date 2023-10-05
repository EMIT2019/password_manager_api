package com.emit.password_manager_api.repository.specification.Parameters;

public enum KeywordParameters {
	KEYWORD_ID_FIELD,
	KEYWORD_OPERATION_FIELD,
	KEYWORD_PLATAFORM_FIELD;
	
	public String getValue() {
		switch(this) {
		case KEYWORD_ID_FIELD:
			return "id_keyword";
		case KEYWORD_OPERATION_FIELD:
			return "operation";
		case KEYWORD_PLATAFORM_FIELD:
			return "plataform";
			default: 
				throw new RuntimeException("The required field does not exists in "+this.getClass()+"");
		}
	}
}
