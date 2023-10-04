package com.emit.password_manager_api.repository.specification.Parameters;

public enum KeywordParameters {
	ID_KEYWORD,
	ID_OPERATION,
	ID_PLATAFORM;
	
	public String getValue() {
		switch(this) {
		case ID_KEYWORD:
			return "id_keyword";
		case ID_OPERATION:
			return "id_operation";
		case ID_PLATAFORM:
			return "id_plataform";
			default: 
				throw new RuntimeException("The required field does not exists in "+this.getClass()+"");
		}
	}
}
