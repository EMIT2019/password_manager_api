package com.emit.password_manager_api.repository.specification.builders;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.emit.password_manager_api.repository.specification.Parameters.*;
import com.emit.password_manager_api.model.AuditKeyword;
import com.emit.password_manager_api.repository.specification.SearchCriteria;
import com.emit.password_manager_api.repository.specification.*;

public class AuditKeywordSpecificationBuilder {
	private final List<SearchCriteria> params;
	
	public AuditKeywordSpecificationBuilder() {
		params = new ArrayList<SearchCriteria>();
	}
	
	public final AuditKeywordSpecificationBuilder with(final String key, final String operation, final Object value) {
		return with(null, key, operation, value, null, null);
	}
	
	public final AuditKeywordSpecificationBuilder with(
			final String orPredicate, 
			final String key, 
			final String operation, 
			final Object value, 
			final @Nullable String prefix, 
			final @Nullable String suffix) {
		OptionParameters op = OptionParameters.getValueByInput(operation.charAt(0));
		
		if(op != null){
            if(op == OptionParameters.EQUALS_TO){
                boolean startsWithAsterisk = prefix != null && prefix.contains(OptionParameters.ZERO_OR_MORE_REGEX);
                boolean endsWithAsterisk = suffix != null && suffix.contains(OptionParameters.ZERO_OR_MORE_REGEX);

                if(startsWithAsterisk && endsWithAsterisk){
                    op = OptionParameters.CONTAINS;
                } else if(startsWithAsterisk){
                    op = OptionParameters.ENDS_WITH;
                } else if(endsWithAsterisk){
                    op = OptionParameters.STARTS_WITH;
                }
            }
            params.add(
              new SearchCriteria(orPredicate, key, op, value)
            );
         }
        return this;
	}
	
	public Specification<AuditKeyword> build(){
        if(params.size() == 0) return null;

        Specification<AuditKeyword> result = new AuditKeywordSpecification(params.get(0));

        for(int i = 1; i < params.size(); i++){
            result = params.get(i).isOrPredicate() ? Specification.where(result).or(new AuditKeywordSpecification(params.get(i))) : Specification.where(result).and(new AuditKeywordSpecification(params.get(i)));
        }

        return result;
    }
}
