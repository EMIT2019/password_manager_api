package com.emit.password_manager_api.repository.specification.Parameters;

public enum PlataformParameters {
	PLATAFORM_ID_FIELD,
	PLATAFORM_NAME_FIELD,
	PLATAFORM_IMG_FIELD;
	
	public String getValue() {
		switch(this) {
		case PLATAFORM_ID_FIELD:
			return "id_plataform";
		case PLATAFORM_NAME_FIELD:
			return "plataform_name";
		case PLATAFORM_IMG_FIELD:
			return "img";
		default:
			throw new RuntimeException("The required field does not exists in "+this.getClass()+"");
		}
	}
}
