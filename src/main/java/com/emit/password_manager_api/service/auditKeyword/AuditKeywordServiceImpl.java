package com.emit.password_manager_api.service.auditKeyword;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;
import java.util.List;
import com.emit.password_manager_api.repository.*;
import com.emit.password_manager_api.service.encrypt.Encrypt;
import com.emit.password_manager_api.service.keyword.KeywordService;
import com.emit.password_manager_api.model.AuditKeyword;
import com.emit.password_manager_api.model.Keyword;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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
		List<AuditKeyword> auditKeywordList = akRepository.findAll();
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuditKeyword save(AuditKeyword entity) {
		return akRepository.save(entity);
	}

	@Override
	public AuditKeyword update(AuditKeyword entity) {
		return akRepository.save(entity);
	}

	@Override
	public void remove(Long id) {
		akRepository.deleteById(id);
	}

	@Override
	public List<AuditKeyword> findByKeyword(Keyword keyword) {
		return akRepository.findByKeyword(keyword);
	}
}
