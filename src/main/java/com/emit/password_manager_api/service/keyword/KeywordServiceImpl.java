package com.emit.password_manager_api.service.keyword;

import java.sql.Date;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;
import com.emit.password_manager_api.repository.*;
import com.emit.password_manager_api.service.auditKeyword.AuditKeywordService;
import com.emit.password_manager_api.service.encrypt.Encrypt;
import com.emit.password_manager_api.model.AuditKeyword;
import com.emit.password_manager_api.model.Keyword;

@Service
public class KeywordServiceImpl implements KeywordService {

	private final KeywordRepository kRepository;
	private final AuditKeywordService akService;
	private final Encrypt<Integer, String> encrypt;
	
	public KeywordServiceImpl(KeywordRepository keywordRepository, AuditKeywordService auditKeywordService, Encrypt<Integer, String> encrypt) {
		this.kRepository = keywordRepository;
		this.akService = auditKeywordService;
		this.encrypt = encrypt;
	}
	
	@Override
	public List<Keyword> findAll() {
		List<Keyword> keywordList = kRepository.findAll().stream()
				.map(this::decryptKeyword)
				.collect(Collectors.toList());
		
		return keywordList;
	}

	@Override
	public Keyword findById(Long id) {
		Optional<Keyword> keyword = kRepository.findById(id);
		
		if(keyword.isPresent()) {
			return this.decryptKeyword(keyword.get());
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
		return kRepository.save(encryptKeyword(entity));
	}

	@Override
	public Keyword update(Keyword entity) {
		return kRepository.save(encryptKeyword(entity));
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

	@Override
	public Keyword encryptKeyword(Keyword keyword) {
		try {
			String salt = "1271872sabsad57XFtsv978SGFGV7";
			SecretKey key = encrypt.getKeyFromPassword(keyword.getKeyword(), salt);
			keyword.setKey(Base64.getEncoder().encodeToString(key.getEncoded()));
			String encryptedKeyword = encrypt.encrypt(keyword.getKeyword(), key);
			keyword.setKeyword(encryptedKeyword);
			return keyword;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		throw new RuntimeException("There was a problem with the saving operation");
	}

	@Override
	public Keyword decryptKeyword(Keyword keyword) {
		try {
			String encodedKey = keyword.getKey();
			byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
			SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
			String decryptedKeyword = encrypt.decrypt(keyword.getKeyword(), key);
			keyword.setKeyword(decryptedKeyword);
			return keyword;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
