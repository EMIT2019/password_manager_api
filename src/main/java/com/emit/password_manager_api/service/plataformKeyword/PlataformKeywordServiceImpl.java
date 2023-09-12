package com.emit.password_manager_api.service.plataformKeyword;

import java.util.List;
import java.util.Optional;

import com.emit.password_manager_api.model.PlataformKeyword;
import com.emit.password_manager_api.repository.PlataformKeywordRepository;

public class PlataformKeywordServiceImpl implements PlataformKeywordService {

	private final PlataformKeywordRepository pkRepository;
	
	public PlataformKeywordServiceImpl(PlataformKeywordRepository plataformKeywordRepository) {
		this.pkRepository = plataformKeywordRepository;
	}
	
	@Override
	public List<PlataformKeyword> findAll() {
		return pkRepository.findAll();
	}

	@Override
	public PlataformKeyword findById(Long id) {
		Optional<PlataformKeyword> plataformKeyword = pkRepository.findById(id);
	
		if(plataformKeyword.isPresent()) {
			return plataformKeyword.get();
		}
		
		throw new RuntimeException("The item with the id "+id+" doesn't exists");
	}

	@Override
	public List<PlataformKeyword> findPage(Integer pageNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlataformKeyword save(PlataformKeyword entity) {
		return pkRepository.save(entity);
	}

	@Override
	public PlataformKeyword update(PlataformKeyword entity) {
		return pkRepository.save(entity);
	}

	@Override
	public void remove(Long id) {
		pkRepository.deleteById(id);
	}

}
