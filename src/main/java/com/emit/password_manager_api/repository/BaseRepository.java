package com.emit.password_manager_api.repository;

import com.emit.password_manager_api.model.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends ModelEntity, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}
