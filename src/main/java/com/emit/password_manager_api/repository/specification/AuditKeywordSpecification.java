package com.emit.password_manager_api.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.emit.password_manager_api.model.AuditKeyword;
import com.emit.password_manager_api.model.Keyword;
import com.emit.password_manager_api.repository.specification.Parameters.KeywordParameters;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class AuditKeywordSpecification implements Specification<AuditKeyword> {
	
	private final SearchCriteria criteria;
	
	public AuditKeywordSpecification(SearchCriteria criteria) {
		this.criteria = criteria;
	}
	
	@Override
	public Specification<AuditKeyword> and(Specification<AuditKeyword> other){
		return Specification.super.and(other);
	}
	
	@Override
	public Specification<AuditKeyword> or(Specification<AuditKeyword> other){
		return Specification.super.or(other);
	}
	
	@Override
	public Predicate toPredicate(Root<AuditKeyword> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		
		try {
			switch(criteria.getOperation()) {
			case EQUALS_TO:
				if(root.get(criteria.getKey()).getJavaType() == Keyword.class) {
					Join<Keyword, AuditKeyword> keywordAuditJoin = root.join(criteria.getKey());
					if(criteria.getValue() != null) {
						return criteriaBuilder.equal(keywordAuditJoin.get(KeywordParameters.ID_KEYWORD.getValue()), criteria.getValue()); 
					}
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	//Method to check if a criteria object is an instance of Integer or String
    // (This method allows the client to make a search by typing a descriptive parameter or simply using a preload catalog)
    public static boolean isNumeric(String value) {
        if(value == null){
            return false;
        }

        try {
            int number = Integer.parseInt(value);
        }catch(NumberFormatException e){
            return false;
        }

        return true;
    }
}
