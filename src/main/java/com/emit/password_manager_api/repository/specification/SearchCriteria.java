package com.emit.password_manager_api.repository.specification;

import com.emit.password_manager_api.repository.specification.Parameters.OptionParameters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {
	private String key;
	private OptionParameters operation;
	private Object value;
	private boolean orPredicate;
	
	public boolean isOrPredicate() {
		return orPredicate;
	}
	
	public SearchCriteria(final String key, final OptionParameters operation, final Object value) {
		super();
		this.key = key;
		this.operation = operation;
		this.value = value;
	}
	
	public SearchCriteria(final String orPredicate, final String key, final OptionParameters operation, final Object value){
        super();
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.orPredicate = orPredicate != null && orPredicate.equals(OptionParameters.OR_PREDICATE_FLAG);
    }
}
