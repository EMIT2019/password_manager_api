package com.emit.password_manager_api.service.keyword;

import java.util.List;
import java.util.Optional;
import com.emit.password_manager_api.repository.*;
import com.emit.password_manager_api.model.Keyword;

public class KeywordServiceImpl implements KeywordService {

	private final KeywordRepository kRepository;
	
	public KeywordServiceImpl(KeywordRepository keywordRepository) {
		this.kRepository = keywordRepository;
	}
	
	@Override
	public List<Keyword> findAll() {
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

}
