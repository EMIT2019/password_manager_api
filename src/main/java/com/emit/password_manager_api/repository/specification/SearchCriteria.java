package com.emit.password_manager_api.repository.specification;

import com.emit.password_manager_api.repository.specification.Parameters.OperationParameters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {
	private String key;
	private OperationParameters operation;
	private Object value;
	private boolean orPredicate;
	
	public boolean isOrPredicate() {
		return orPredicate;
	}
	
	public SearchCriteria(final String key, final OperationParameters operation, final Object value) {
		super();
		this.key = key;
		this.operation = operation;
		this.value = value;
	}
	
	public SearchCriteria(final String orPredicate, final String key, final OperationParameters operation, final Object value){
        super();
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.orPredicate = orPredicate != null && orPredicate.equals(OperationParameters.OR_PREDICATE_FLAG);
    }
}
