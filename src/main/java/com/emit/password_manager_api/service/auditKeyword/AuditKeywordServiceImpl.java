package com.emit.password_manager_api.service.auditKeyword;

import java.util.Optional;
import java.util.List;
import com.emit.password_manager_api.repository.*;
import com.emit.password_manager_api.model.AuditKeyword;
import org.springframework.stereotype.Service;

@Service
public class AuditKeywordServiceImpl implements AuditKeywordService {
	
	private final AuditKeywordRepository akRepository;

	public AuditKeywordServiceImpl(AuditKeywordRepository auditKeywordRepository) {
		this.akRepository = auditKeywordRepository;
	}
	
	@Override
	public List<AuditKeyword> findAll() {
		return akRepository.findAll();
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

}
