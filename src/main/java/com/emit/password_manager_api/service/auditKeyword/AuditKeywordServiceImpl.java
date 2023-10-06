package com.emit.password_manager_api.service.auditKeyword;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Date;
import java.util.List;
import com.emit.password_manager_api.repository.*;
import com.emit.password_manager_api.repository.specification.SearchCriteria;
import com.emit.password_manager_api.repository.specification.Parameters.AuditKeywordParameters;
import com.emit.password_manager_api.repository.specification.Parameters.OptionParameters;
import com.emit.password_manager_api.repository.specification.builders.AuditKeywordSpecificationBuilder;
import com.emit.password_manager_api.service.encrypt.Encrypt;
import com.emit.password_manager_api.service.parameters.GlobalServiceParameters;
import com.emit.password_manager_api.model.AuditKeyword;
import com.emit.password_manager_api.model.Keyword;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.emit.password_manager_api.repository.specification.AuditKeywordSpecification;

@Service
public class AuditKeywordServiceImpl implements AuditKeywordService {
	
	private final AuditKeywordRepository akRepository;
	private final Encrypt<Integer, String, Keyword> encrypt;

	public AuditKeywordServiceImpl(AuditKeywordRepository auditKeywordRepository, Encrypt<Integer, String, Keyword> encrypt) {
		this.encrypt = encrypt;
		this.akRepository = auditKeywordRepository;
	}
	
	@Override
	public List<AuditKeyword> findAll() {
		List<AuditKeyword> auditKeywordList = this.decryptAuditKeyword(akRepository.findAll());
		
		return auditKeywordList;
	}

	@Override
	public AuditKeyword findById(Long id) {
		Optional<AuditKeyword> auditKeyword = akRepository.findById(id);
		
		if(auditKeyword.isPresent()) {
			return auditKeyword.get();
		}
		
		throw new RuntimeException("This item with id "+id+" doesn't exists");
	}

	@Override
	public List<AuditKeyword> findPage(Integer pageNumber) {
		Pageable page = PageRequest.of(pageNumber, GlobalServiceParameters.SMALL_RECORDS_AMOUNT.getValue());
		return akRepository.findAll(page).getContent();
	}

	@Override
	public AuditKeyword save(AuditKeyword entity) {
		return akRepository.save(entity);
	}

	@Override
	public AuditKeyword update(AuditKeyword entity) {
		return null;
	}

	@Override
	public void remove(Long id) {
		akRepository.deleteById(id);
	}

	@Override
	public List<AuditKeyword> findByKeyword(Integer pageNumber, Keyword keyword) {
		
		List<AuditKeyword> auditKeywordList;
		
		SearchCriteria criteria = new SearchCriteria(
				AuditKeywordParameters.AUDIT_KEYWORD_FIELD.getValue(),
				OptionParameters.EQUALS_TO,
				keyword.getId_keyword()
				);
		
		Pageable page = PageRequest.of(pageNumber, GlobalServiceParameters.SMALL_RECORDS_AMOUNT.getValue());
		
		auditKeywordList = akRepository.findAll(new AuditKeywordSpecification(criteria), page).getContent();
		
		return this.decryptAuditKeyword(auditKeywordList);
	}

	@Override
	public List<AuditKeyword> findAuditKeywordByDate(Integer pageNumber, Date date) {
		
		List<AuditKeyword> auditKeywordList;
		
		SearchCriteria criteria = new SearchCriteria(
				AuditKeywordParameters.AUDIT_DATE_FIELD.getValue(),
				OptionParameters.EQUALS_TO,
				date
				);
		
		Pageable page = PageRequest.of(pageNumber, GlobalServiceParameters.SMALL_RECORDS_AMOUNT.getValue());
		
		auditKeywordList = akRepository.findAll(new AuditKeywordSpecification(criteria), page).getContent();
		
		return this.decryptAuditKeyword(auditKeywordList);
	}
	
	@Override
	public List<AuditKeyword> globalSearch(Integer pageNumber, String searchParams) {
		AuditKeywordSpecificationBuilder builder = new AuditKeywordSpecificationBuilder();
		Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
		Matcher matcher = pattern.matcher(searchParams + ",");
		
		while(matcher.find()) {
			System.out.println(matcher.group(1) +" "+ matcher.group(2) +" "+ matcher.group(3));
			builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
		}
		
		Pageable page = PageRequest.of(pageNumber, GlobalServiceParameters.HUGE_RECORDS_AMOUNT.getValue());
		
		Specification<AuditKeyword> spec = builder.build();
		
		return akRepository.findAll(spec, page).getContent();
	}

	@Override
	public List<AuditKeyword> decryptAuditKeyword(List<AuditKeyword> auditKeywordList) {
		
		for(int x = 0; x < auditKeywordList.size(); x++) {
			Keyword keyword = new Keyword();
			keyword.setKey(auditKeywordList.get(x).getKeyword().getKey());
			keyword.setKeyword(auditKeywordList.get(x).getKeyword().getKeyword());
			String decryptedKeyword = encrypt.decryptKeyword(keyword).getKeyword();
			auditKeywordList.get(x).setKeyword_value(decryptedKeyword);
		}
		
		return auditKeywordList;
	}
}
