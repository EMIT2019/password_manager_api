package com.emit.password_manager_api.service.operation;

import java.util.List;
import java.util.Optional;
import com.emit.password_manager_api.repository.*;
import com.emit.password_manager_api.model.Operation;

public class OperationServiceImpl implements OperationService {
	
	private final OperationRepository oRepository;
	
	public OperationServiceImpl(OperationRepository operationRepository) {
		this.oRepository = operationRepository;
	}

	@Override
	public List<Operation> findAll() {
		return oRepository.findAll();
	}

	@Override
	public Operation findById(Long id) {
		Optional<Operation> operation = oRepository.findById(id);
		
		if(operation.isPresent()) {
			return operation.get();
		}
		
		throw new RuntimeException("The item with the id "+id+" doesn't exists");
	}

	@Override
	public List<Operation> findPage(Integer pageNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operation save(Operation entity) {
		return oRepository.save(entity);
	}

	@Override
	public Operation update(Operation entity) {
		return oRepository.save(entity);
	}

	@Override
	public void remove(Long id) {
		oRepository.deleteById(id);
	}

}
