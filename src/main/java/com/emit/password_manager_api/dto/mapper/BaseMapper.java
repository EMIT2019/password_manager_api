package com.emit.password_manager_api.dto.mapper;

import com.emit.password_manager_api.dto.*;
import com.emit.password_manager_api.model.*;

public interface BaseMapper<T extends ModelEntity, E extends DtoEntity> {
	E toGetDtoEntity(T entity);
	
	T toPostEntity(E dto);
}
