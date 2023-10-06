package com.emit.password_manager_api.repository.specification;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.emit.password_manager_api.model.AuditKeyword;
import com.emit.password_manager_api.model.Keyword;
import com.emit.password_manager_api.repository.specification.Parameters.AuditKeywordParameters;
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
			//Search a record with any kind of information provided by the client
			case EQUALS_TO:
				//Search an audit by the keyword
				if(root.get(criteria.getKey()).getJavaType() == Keyword.class) {
					//Making a join from AuditKeyword to Keyword to make a search based on the keyword provided
					Join<Keyword, AuditKeyword> keywordAuditJoin = root.join(criteria.getKey());
					
					//Based on the information provided, here it makes a search based on the selected primary key keyword
					if(criteria.getValue() != null) {
						return criteriaBuilder.equal(keywordAuditJoin.get(KeywordParameters.KEYWORD_ID_FIELD.getValue()), criteria.getValue()); 
					}
				
					//Search records based on the date
				} else if(root.get(criteria.getKey()).getJavaType() == Date.class) {
					
					if(criteria.getValue() != null) {
						//We pass the object field we want to make the comparison with and the value from the client.
						return criteriaBuilder.equal(root.get(AuditKeywordParameters.AUDIT_DATE_FIELD.getValue()), criteria.getValue());
					}
					
				} else if(root.get(criteria.getKey()).getJavaType() == String.class){
                    return criteriaBuilder.like(
                            root.<String> get(criteria.getKey()), "%" + criteria.getValue() + "%"
                    );
                } else {
                    return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
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
