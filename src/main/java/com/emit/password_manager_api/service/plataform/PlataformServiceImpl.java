package com.emit.password_manager_api.service.plataform;

import java.util.List;
import com.emit.password_manager_api.repository.*;
import com.emit.password_manager_api.model.Plataform;
import java.util.Optional;

public class PlataformServiceImpl implements PlataformService {
	
	private final PlataformRepository pRepository;
	
	public PlataformServiceImpl(PlataformRepository plataformRepository) {
		this.pRepository = plataformRepository;
	}

	@Override
	public List<Plataform> findAll() {
		return pRepository.findAll();
	}

	@Override
	public Plataform findById(Long id) {
		Optional<Plataform> plataform = pRepository.findById(id);
		
		if(plataform.isPresent()) {
			return plataform.get();
		}
		
		throw new RuntimeException("The item with id "+id+" doesn't exists.");
	}

	@Override
	public List<Plataform> findPage(Integer pageNumber) {
		return null;
	}

	@Override
	public Plataform save(Plataform entity) {
		return pRepository.save(entity);
	}

	@Override
	public Plataform update(Plataform entity) {
		return pRepository.save(entity);
	}

	@Override
	public void remove(Long id) {
		pRepository.deleteById(id);
	}

}
