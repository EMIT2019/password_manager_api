package com.emit.password_manager_api.service.keyword;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.emit.password_manager_api.repository.*;
import com.emit.password_manager_api.service.auditKeyword.AuditKeywordService;
import com.emit.password_manager_api.model.AuditKeyword;
import com.emit.password_manager_api.model.Keyword;

@Service
public class KeywordServiceImpl implements KeywordService {

	private final KeywordRepository kRepository;
	private final AuditKeywordService akService;
	
	public KeywordServiceImpl(KeywordRepository keywordRepository, AuditKeywordService auditKeywordService) {
		this.kRepository = keywordRepository;
		this.akService = auditKeywordService;
	}
	
	@Override
	public List<Keyword> findAll() {
		System.out.print(kRepository.findAll().get(0));
		return kRepository.findAll();
	}

	@Override
	public Keyword findById(Long id) {
		Optional<Keyword> keyword = kRepository.findById(id);
		
		if(keyword.isPresent()) {
			return keyword.get();
		}
		
		throw new RuntimeException("The item with id "+id+" doesn't exists");
	}

	@Override
	public List<Keyword> findPage(Integer pageNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Keyword save(Keyword entity) {
		return kRepository.save(entity);
	}

	@Override
	public Keyword update(Keyword entity) {
		return kRepository.save(entity);
	}

	@Override
	public void remove(Long id) {
		kRepository.deleteById(id);
	}

	@Override
	public void auditKeyword(Keyword keyword) {
		
		AuditKeyword auditKeyword = new AuditKeyword();
		
		java.util.Date date = new java.util.Date();
		Date formattedDate = new Date(date.getTime());
		
		auditKeyword.setKeyword(keyword);
		auditKeyword.setOperation(keyword.getOperation());
		auditKeyword.setId_plataform(keyword.getPlataform().getId_plataform());
		auditKeyword.setKeyword_value(keyword.getKeyword());
		auditKeyword.setAction_date(formattedDate);
		
		akService.save(auditKeyword);
	}

}
