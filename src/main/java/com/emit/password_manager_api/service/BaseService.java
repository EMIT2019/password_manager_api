package com.emit.password_manager_api.service;

import com.emit.password_manager_api.model.*;
import java.util.List;

public interface BaseService<T extends ModelEntity, ID> {
	List<T> findAll();
	
	T findById(ID id);
	
	List<T> findPage(Integer pageNumber);
	
	T save(T entity);
	
	T update(T entity);
	
	void remove(ID id);
}
