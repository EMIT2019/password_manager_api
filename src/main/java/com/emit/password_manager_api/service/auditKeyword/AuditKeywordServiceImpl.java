package com.emit.password_manager_api.service.auditKeyword;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.emit.password_manager_api.repository.*;
import com.emit.password_manager_api.repository.specification.SearchCriteria;
import com.emit.password_manager_api.repository.specification.Parameters.AuditKeywordParameters;
import com.emit.password_manager_api.repository.specification.Parameters.OptionParameters;
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
	public List<AuditKeyword> decryptAuditKeyword(List<AuditKeyword> auditKeywordList) {
		
		for(int x = 0; x < auditKeywordList.size(); x++) {
			Keyword keyword = new Keyword();
			keyword.setKey(auditKeywordList.get(x).getKey());
			keyword.setKeyword(auditKeywordList.get(x).getKeyword_value());
			String decryptedKeyword = encrypt.decryptKeyword(keyword).getKeyword();
			auditKeywordList.get(x).setKeyword_value(decryptedKeyword);
		}
		
		return auditKeywordList;
	}

	@Override
	public List<AuditKeyword> findAuditKeywordBetweenDates(Integer pageNumber, Date startDate, Date endDate) {
		
		List<AuditKeyword> auditKeywordList;
		ArrayList<Date> dates = new ArrayList<>();
		
		dates.add(startDate);
		dates.add(endDate);
		
		SearchCriteria criteria = new SearchCriteria(
				AuditKeywordParameters.AUDIT_DATE_FIELD.getValue(),
				OptionParameters.EQUALS_TO,
				dates
				);
		
		
		Pageable page = PageRequest.of(pageNumber, GlobalServiceParameters.SMALL_RECORDS_AMOUNT.getValue());
		
		auditKeywordList = akRepository.findAll(new AuditKeywordSpecification(criteria), page).getContent();
		
		return this.decryptAuditKeyword(auditKeywordList);
	}
}
